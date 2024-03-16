package br.com.lucassoaresdev.gestao_vagas.modules.company.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Schema(example = "expert", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    @Schema(example = "expert@expert.com.br", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 e 100 caracteres ")
    @Schema(example = "expert1234", minLength = 10, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(example = "expert.com.br", requiredMode = Schema.RequiredMode.REQUIRED)
    private String website;
    @Schema(example = "Expert TI", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(example = "Empresa de tecnologia", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
