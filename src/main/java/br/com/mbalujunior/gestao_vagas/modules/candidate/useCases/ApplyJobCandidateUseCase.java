package br.com.mbalujunior.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mbalujunior.gestao_vagas.exceptions.JobNotFoundException;
import br.com.mbalujunior.gestao_vagas.exceptions.UserNotFoundException;
import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateRepository;
import br.com.mbalujunior.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.mbalujunior.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.mbalujunior.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private candidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;

    public ApplyJobEntity execute(UUID idCandidato, UUID idJob) {
        // Validar se o candidato existe
        var candidate = this.candidateRepository.findById(idCandidato)
            .orElseThrow(() -> new UserNotFoundException());

        // Validar se a vaga existe
        var job = this.jobRepository.findById(idJob)
            .orElseThrow(() -> new JobNotFoundException());

        // Criar a inscrição na vaga
        var applyJob = ApplyJobEntity.builder()
            .candidate(candidate)   // Usar a entidade diretamente
            .job(job)               // Usar a entidade diretamente
            .build();

        // Salvar a inscrição
        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
