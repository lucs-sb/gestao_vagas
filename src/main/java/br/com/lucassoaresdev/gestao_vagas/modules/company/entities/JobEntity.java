package br.com.lucassoaresdev.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name ="job")
@Getter
@Setter
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String level;
    private String benefits;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity companyEntity;
    @Column(name = "company_id")
    private UUID companyId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}