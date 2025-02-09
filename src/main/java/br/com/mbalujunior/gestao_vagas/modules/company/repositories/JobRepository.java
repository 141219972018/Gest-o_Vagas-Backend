package br.com.mbalujunior.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mbalujunior.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
    
    //"contains - LIKE"
    //pega na minha tabela jobERntity todos os trabalho com o nome passado pelo user 
    //e devolve essa lista para o usuario
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
