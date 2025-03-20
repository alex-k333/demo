package com.provision.demo.service;

import com.provision.demo.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

    private final String jwtSecret;
    private final JwtParserCreator jwtParserCreator;

    public JwtProvider(@Value("${jwt.secret}") String jwtSecret, JwtParserCreator jwtParserCreator) {
        this.jwtSecret = jwtSecret;
        this.jwtParserCreator = jwtParserCreator;
    }

    public String generateToken(User user) {
        final LocalDateTime now = LocalDateTime.now();
        final Date accessExpiration = Date.from(now.plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(user.getUsername())
                .expiration(accessExpiration)
                .signWith(getSigningKey())
                .claim("role", user.getRole())
                .compact();
    }

    public String fetchUserName(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails user) {
        JwtParser jwtParser = jwtParserCreator.get(getSigningKey());
        return tryParse(token, jwtParser) && StringUtils.equals(fetchUserName(token), user.getUsername());
    }

    private boolean tryParse(String token, JwtParser jwtParser) {
        try {
            jwtParser.parse(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Токен истёк", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Тип токена не поддерживается", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Неправильно сформированный токен", mjEx);
        } catch (SignatureException sEx) {
            log.error("Невалидная подпись", sEx);
        } catch (Exception e) {
            log.error("Невалидный токен", e);
        }
        return false;
    }

    private Claims extractClaims(String token) {
        return jwtParserCreator.get(getSigningKey())
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
