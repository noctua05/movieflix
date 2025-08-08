package com.movieflix.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.movieflix.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenService {

    @Value("${movieflix.security.secret}")
    private String secrete;

    public String generateToken(UserEntity user){
        Algorithm algorithm = Algorithm.HMAC256("");

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("UserId",user.getId())
                .withClaim("UserName",user.getName())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("API Movieflix")
                .sign(algorithm);
    }

}
