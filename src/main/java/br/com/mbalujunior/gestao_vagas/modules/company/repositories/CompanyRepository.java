package br.com.mbalujunior.gestao_vagas.modules.company.repositories;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbalujunior.gestao_vagas.modules.company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID>{
      //estou buscando o nome e o email se ja existe na base de dados
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
    Optional<CompanyEntity> findByUsername(String username);
}
