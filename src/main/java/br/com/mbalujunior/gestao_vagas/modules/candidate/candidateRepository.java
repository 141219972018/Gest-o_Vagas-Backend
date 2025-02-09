package br.com.mbalujunior.gestao_vagas.modules.candidate;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface candidateRepository extends JpaRepository<candidateEntity, UUID>{

    //estou buscando o nome e o email se ja existe na base de dados
    Optional<candidateEntity> findByUsernameOrEmail(String username, String email);
    Optional<candidateEntity>findByUsername(String username);
}
