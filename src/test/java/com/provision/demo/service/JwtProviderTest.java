package com.provision.demo.service;

import com.provision.demo.model.entity.User;
import com.provision.demo.model.enumiration.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtProviderTest {
    private static final String SECRET = "3ea1a6e09fca79b5426bc0eb7fd623535b77" +
                                         "fa392527fcdbc2255e290ce28d06e08fc6403700e29dd51b3" +
                                         "999a48fa5d96332ef48627a530f649c55af88070582ead0d6" +
                                         "f355281dcb92cbe0ddce3fd15dd5f386af2f30020af7d4bda" +
                                         "2eb41efc241847eb63d9fa09fb00ceaeba7c714f46d3cfdbc" +
                                         "a1591cd7430b0d8741a120c11204a5641416c1bfc9afb6049" +
                                         "6942f33a7e2f60544374b2fdb6f3acbd5a695440c0a03d3d9" +
                                         "25cf5363c7ca470499bc5e9fbe09750dec35d4784a507e25a" +
                                         "7f3dcc719e311912a2aa75a3578651520233eae93dece2baf" +
                                         "88daf25b801daab76530ffd00e501adfd2f3a21e92504a0b8" +
                                         "499de2fa11af513bdcc1158a8c57310c14f";

    private static final String USER_NAME = "username";

    @Mock
    private JwtParserCreator jwtParserCreator;

    private JwtProvider provider;

    @BeforeEach
    void setUp() {
        provider = new JwtProvider(SECRET, jwtParserCreator);
    }

    @Test
    void generateToken() {
        String token = provider.generateToken(User.builder()
                .username(USER_NAME)
                .role(Role.ADMIN)
                .build());
        assertThat(token).isNotBlank();
    }

    @Test
    void fetchUserName() {
        when(jwtParserCreator.get(any())).thenCallRealMethod();
        final String token = provider.generateToken(User.builder()
                .username(USER_NAME)
                .role(Role.ADMIN)
                .build());
        assertThat(token).isNotBlank();

        assertThat(provider.fetchUserName(token)).isEqualTo(USER_NAME);
    }

    @Test
    void validateToken() {
        when(jwtParserCreator.get(any())).thenCallRealMethod();
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(USER_NAME)
                .password("password")
                .roles(Role.MANAGER.name())
                .build();
        final String token = provider.generateToken(User.builder()
                .username(USER_NAME)
                .role(Role.ADMIN)
                .build());

        assertThat(provider.validateToken(token, userDetails)).isTrue();

        validateTokenFalse(ExpiredJwtException.class, token, userDetails);
        validateTokenFalse(UnsupportedJwtException.class, token, userDetails);
        validateTokenFalse(MalformedJwtException.class, token, userDetails);
        validateTokenFalse(SignatureException.class, token, userDetails);
        validateTokenFalse(RuntimeException.class, token, userDetails);

        UserDetails falseDetails = org.springframework.security.core.userdetails.User.builder()
                .username("unknown")
                .password("password")
                .roles(Role.MANAGER.name())
                .build();
        assertThat(provider.validateToken(token, falseDetails)).isFalse();
    }

    private <T extends Exception> void validateTokenFalse(Class<T> exceptionClass, String token, UserDetails userDetails) {
        JwtParser parserMock = mock(JwtParser.class);
        when(parserMock.parse(anyString())).thenThrow(exceptionClass);
        when(jwtParserCreator.get(any())).thenReturn(parserMock);
        assertThat(provider.validateToken(token, userDetails)).isFalse();
    }
}