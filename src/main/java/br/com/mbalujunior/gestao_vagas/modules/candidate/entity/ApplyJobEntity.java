package br.com.mbalujunior.gestao_vagas.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.mbalujunior.gestao_vagas.modules.candidate.candidateEntity;
import br.com.mbalujunior.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Data;

@Entity(name = "apply_jobs")
@Data
@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class ApplyJobEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private candidateEntity candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity job;

    @Column(name = "candidate_id")
    private UUID candidateId;

    @Column(name = "job_id")
    private UUID jobId;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
