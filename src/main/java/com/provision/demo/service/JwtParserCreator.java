package com.provision.demo.service;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Optional;

@Component
public class JwtParserCreator {

    public JwtParser get(SecretKey secretKey) {
        return Optional.ofNullable(secretKey)
                .map(key -> Jwts.parser()
                        .verifyWith(secretKey)
                        .build())
                .orElse(null);
    }
}
