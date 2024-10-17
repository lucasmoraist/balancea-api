package com.lucasmoraist.balancea.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
public class TokenService {

    @Value("${jwt.secret")
    private String secret;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        this.algorithm = Algorithm.HMAC256(this.secret);
    }

    public String generateToken(String email) {
        try {
            String token = JWT.create()
                    .withIssuer("Balancea API")
                    .withSubject(email)
                    .withExpiresAt(this.genExpirationDate())
                    .sign(algorithm);

            log.info("Token gerado com sucesso para o email: {}", email);
            return token;
        } catch (JWTCreationException e) {
            log.error("Erro ao gerar token para o email: {}", email, e);
            throw new RuntimeException("Erro ao gerar token: ", e);
        }
    }

    public String validateToken(String token) {
        try {
            String subject = JWT.require(algorithm)
                    .withIssuer("Balancea API")
                    .build()
                    .verify(token)
                    .getSubject();

            log.info("Token validado com sucesso: {}", token);
            return subject;
        } catch (JWTVerificationException e) {
            log.warn("Token inválido: {}", token);
            return null;
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.of("-03:00"));
    }

}
