package br.com.mbalujunior.gestao_vagas.modules.candidate.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateRepository;
import br.com.mbalujunior.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.mbalujunior.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;


@RestController
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String SecretKey;
    
    @Autowired
    private  candidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public  AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
            .orElseThrow(() -> {  
                throw new UsernameNotFoundException("Username/password incorrect");
              });
              var passwordMatches = this.passwordEncoder
                .matches(authCandidateRequestDTO.password(), candidate.getPassword());

                if(!passwordMatches){
                    throw new AuthenticationException();
                }
                Algorithm algorithm= Algorithm.HMAC256(SecretKey);
                var expiresIn = Instant.now().plus(Duration.ofMinutes(20));
                var token = JWT.create()
                    .withIssuer("javagas")
                    .withSubject(candidate.getId().toString())
                    .withClaim("roles", Arrays.asList("CANDIDATE"))
                    .withExpiresAt(expiresIn)
                    .sign(algorithm);

                var autCandidateResponse = AuthCandidateResponseDTO.builder()
                    .access_token(token)
                    .build();
                return autCandidateResponse;
        
    }
}
