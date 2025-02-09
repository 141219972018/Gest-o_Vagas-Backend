package br.com.mbalujunior.gestao_vagas.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
    
    // Método para converter objetos em JSON
    public static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj); // Converte o objeto em String JSON
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON", e); // Mensagem mais clara em caso de erro
        }
    }

    // Método para gerar token JWT com base no ID da empresa e no segredo
    public static String generateToken(UUID idCompany, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret); // Algoritmo HMAC com o segredo

        // Define a data de expiração para o token (2 horas após a criação)
        Instant expiresIn = Instant.now().plus(Duration.ofHours(2));

        // Cria e assina o token com as informações necessárias
        String token = JWT.create()
            .withIssuer("javagas")
            .withSubject(idCompany.toString()) // Associa o ID da empresa como o sujeito
            .withExpiresAt(expiresIn) // Define o tempo de expiração
            .withClaim("roles", Arrays.asList("COMPANY")) // Define a role como "COMPANY"
            .sign(algorithm); // Assina o token

        return token; // Retorna o token gerado
    }
}
