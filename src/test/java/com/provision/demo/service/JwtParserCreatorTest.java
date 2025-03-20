package com.provision.demo.service;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class JwtParserCreatorTest {

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

    @Test
    void get() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        SecretKey keys = Keys.hmacShaKeyFor(keyBytes);
        assertThat(new JwtParserCreator().get(keys)).isNotNull();
    }
}