package br.com.mbalujunior.gestao_vagas.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateEntity;
import br.com.mbalujunior.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.mbalujunior.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.mbalujunior.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.mbalujunior.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import br.com.mbalujunior.gestao_vagas.modules.company.entities.JobEntity;
import br.com.mbalujunior.gestao_vagas.modules.company.useCases.ListAllJobsByFilterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class candidateController {

 
    @Autowired 
    private CreateCandidateUseCase CreateCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @Autowired
    private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

    @Autowired
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

   @PostMapping("/")
   public ResponseEntity<Object> create(@Valid @RequestBody candidateEntity candidateEntity) {
        try{
            var result= this.CreateCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.ok().body(result);

         }catch(Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
         }
       
   }

   @GetMapping("/")
   @PreAuthorize("hasRole('CANDIDATE')")
   @Tag(name = "Candidato", description = "Informações do candidato")
   @Operation(summary= "Perfil do Candidato",
            
             description = " Essa função é responavel por buscar informações do perfil do candidato ")
   @ApiResponses({ 
             @ApiResponse(responseCode="200",content={     
                @Content(schema=@Schema(implementation=ProfileCandidateResponseDTO.class))
                
            }),
            @ApiResponse(responseCode="400",description= "user not found")
        })  
   
  @SecurityRequirement(name="jwt_auth")
   public ResponseEntity<Object> get(HttpServletRequest request){ 
        var idcandidate = request.getAttribute("candidate_id");

        try {
            var profile = this.profileCandidateUseCase
                .execute(UUID.fromString(idcandidate.toString()));
            return  ResponseEntity.ok().body(profile);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //vai pesquisar os jobs usando os nomes dos jobs

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Tag(name = "Candidato", description = "Informações do candidato")
    @Operation(summary= "Listagem de vagas disponivel para o candidato", description = " Essa função é responavel por listar baseada no filtro ")
    @ApiResponses({ 
        @ApiResponse(responseCode="200",content={     
            @Content(array= @ArraySchema(schema=@Schema(implementation=JobEntity.class))
            )
        })
    })
    @SecurityRequirement(name="jwt_auth")
    public List<JobEntity> findJobByFilter(@RequestParam String filter){ 
        return this.listAllJobsByFilterUseCase.execute(filter);
    }

   @PostMapping("/job/apply")
   @PreAuthorize("hasRole('CANDIDATE")
   @Operation(summary= "Inscrição de candiodato para vaga",
             description = " Essa função é Responsavél por realizar a Inscrição do candidato em uma vaga ")
             @SecurityRequirement(name="jwt_auth")
    public ResponseEntity<Object> applyJob(HttpServletRequest request,@RequestBody UUID idJob){
           var idCandidate= request.getAttribute("candidate_id");

           try {
                var result = this.applyJobCandidateUseCase.execute(UUID.fromString(idCandidate.toString()),
                    idJob);
                    return ResponseEntity.ok().body(result);

           } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
           }
    } 

}
