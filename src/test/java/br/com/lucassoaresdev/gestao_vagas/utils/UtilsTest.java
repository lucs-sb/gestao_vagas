package br.com.lucassoaresdev.gestao_vagas.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UtilsTest {

    public static String objectToJSON(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID companyId) {
        Algorithm algorithm = Algorithm.HMAC256("JAVAGAS_@123#");

        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        return JWT.create().withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withSubject(companyId.toString())
                .withClaim("roles", List.of("COMPANY"))
                .sign(algorithm);
    }

}
