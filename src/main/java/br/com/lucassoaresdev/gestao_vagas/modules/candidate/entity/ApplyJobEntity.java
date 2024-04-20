package br.com.lucassoaresdev.gestao_vagas.modules.candidate.entity;

import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.JobEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "apply_jobs")
@Getter
@Setter
public class ApplyJobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "candidate_id", insertable = false, updatable = false)
    private CandidateEntity candidate;
    @ManyToOne
    @JoinColumn(name = "job_id", insertable = false, updatable = false)
    private JobEntity job;
    @Column(name = "candidate_id")
    private UUID candidateId;
    @Column(name = "job_id")
    private UUID jobId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
