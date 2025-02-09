package br.com.mbalujunior.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mbalujunior.gestao_vagas.exceptions.UserFoundException;
import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateEntity;
import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private candidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /*O método verifica se um utilizador com o mesmo nome de
    utilizador ou email já existe, e lança uma exceção se for o caso.
    Caso contrário, prossegue para salvar o novo candidateEntity. */
    public candidateEntity execute(candidateEntity candidateEntity){
         this.candidateRepository
        .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user)->{
            throw new UserFoundException();
        });
        
        //encriptando a palavra passe do candidato
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
   }

    

}
