package com.lucasmoraist.balancea.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucasmoraist.balancea.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenService {

    @Value("${jwt.secret")
    private String secret;

    public String generateToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            String token = JWT.create()
                    .withIssuer("Balancea API")
                    .withSubject(email)
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
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
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


}
