package br.edu.unifacisa.projeto_integrador.token;

import br.edu.unifacisa.projeto_integrador.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret-key}")
    private String secretKey;

    public String create(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT
                    .create()
                    .withIssuer("integrative-project")
                    .withSubject(user.getUsername())
                    .withExpiresAt(genExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error creating JWT", exception);
        }
    }

    public String verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT
                    .require(algorithm)
                    .withIssuer("integrative-project")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error verifying JWT", exception);
        }
    }

    private Instant genExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}