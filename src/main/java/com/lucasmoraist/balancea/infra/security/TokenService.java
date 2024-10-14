package com.lucasmoraist.balancea.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucasmoraist.balancea.domain.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${jwt.secret")
    private String secret;

    public String generateToken(String email) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer("Balancea API")
                    .withSubject(email)
                    .sign(algorithm)
                    ;
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token: ", e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer("Balancea API")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

}
