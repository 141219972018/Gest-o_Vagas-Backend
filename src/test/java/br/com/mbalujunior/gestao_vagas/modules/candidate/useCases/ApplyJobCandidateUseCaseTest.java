package br.com.mbalujunior.gestao_vagas.modules.candidate.useCases;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.mbalujunior.gestao_vagas.exceptions.JobNotFoundException;
import br.com.mbalujunior.gestao_vagas.exceptions.UserNotFoundException;
import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateEntity;
import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateRepository;
import br.com.mbalujunior.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.mbalujunior.gestao_vagas.modules.company.repositories.JobRepository;



@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    public ApplyJobCandidateUseCase applyJobCandidateUseCase;


   @Mock// informando que é uma dependencia do injectmock
    private candidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;
    
@Test
@DisplayName("should_not_be_able_to_apply_job_with_candidate_not_found")
public void should_not_be_able_to_apply_job_with_candidate_not_found() {
    try {
        // Chama o método com valores nulos, o que deve disparar a exceção
        applyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
        // Verifica se a exceção é do tipo UserNotFoundException
        assertThat(e).isInstanceOf(UserNotFoundException.class);  // Agora verifica UserNotFoundException
    }
}


    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found(){
        var idCandidate =UUID.randomUUID();

        var candidate = new candidateEntity();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        }catch(Exception e){
                 assertThat(e).isInstanceOf(JobNotFoundException.class);

        }

    }

    /* @Test
public void should_be_able_to_create_a_new_apply_job(){

    var idCandidate = UUID.randomUUID(); 
    var idJob = UUID.randomUUID(); 
   

    var applyJob = ApplyJobEntity.builder().candidadateId(idCandidate)
        .jobId(idJob)
        .build();

    var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();
    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new candidateEntity()));

 
    when (jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity())); 

    when (applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

    var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(result.getId());
}*/


}
