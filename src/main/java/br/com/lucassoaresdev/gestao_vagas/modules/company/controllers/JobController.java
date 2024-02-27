package br.com.lucassoaresdev.gestao_vagas.modules.company.controllers;

import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO jobDTO, HttpServletRequest request) {

        JobEntity job = JobEntity.builder()
                .companyId(UUID.fromString(request.getAttribute("company_id").toString()))
                .benefits(jobDTO.benefits())
                .description(jobDTO.description())
                .level(jobDTO.level())
                .build();

        return ResponseEntity.accepted().body(this.createJobUseCase.execute(job));
    }
}
