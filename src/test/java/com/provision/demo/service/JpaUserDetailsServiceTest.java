package com.provision.demo.service;

import com.provision.demo.model.entity.User;
import com.provision.demo.model.enumiration.Role;
import com.provision.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JpaUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private JpaUserDetailsService jpaUserDetailsService;

    @Test
    void loadUserByUsername_whenNameOk_thenUserLoaded() {
        User testUser = User.builder()
                .username("username")
                .password("password")
                .role(Role.ADMIN)
                .build();
        when(userRepository.findUserByUsername(anyString())).thenReturn(Optional.of(testUser));

        UserDetails user = jpaUserDetailsService.loadUserByUsername(testUser.getUsername());

        assertThat(user.getUsername()).isEqualTo(testUser.getUsername());
        assertThat(user.getPassword()).isEqualTo(testUser.getPassword());
        assertThat(user.getAuthorities()).allMatch(
                grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
}