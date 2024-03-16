package br.com.lucassoaresdev.gestao_vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name ="job")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Schema(example = "Vaga para pessoa desenvolvedora júnior")
    private String description;
    @NotBlank(message = "Campo obrigatório")
    @Schema(example = "JUNIOR")
    private String level;
    @Schema(example = "GYMPass, Plano de saúde")
    private String benefits;
    @ManyToOne
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;
    @Column(name = "company_id", nullable = false)
    private UUID companyId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
