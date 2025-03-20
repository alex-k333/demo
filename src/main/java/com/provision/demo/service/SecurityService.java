package com.provision.demo.service;

import com.provision.demo.exception.LoginException;
import com.provision.demo.model.LoginRequest;
import com.provision.demo.model.LoginResponse;
import com.provision.demo.model.entity.Token;
import com.provision.demo.model.entity.User;
import com.provision.demo.repository.TokenRepository;
import com.provision.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SecurityService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    @SneakyThrows
    public LoginResponse login(LoginRequest loginRequest) {
        final User user = userRepository.findUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new LoginException("Проверьте логин и пароль"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = jwtProvider.generateToken(user);
            tokenRepository.save(Token.builder()
                    .token(token)
                    .user(user)
                    .build());
            return new LoginResponse(token);
        } else {
            throw new LoginException("Проверьте логин и пароль");
        }
    }
}
