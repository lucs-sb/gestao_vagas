package br.com.lucassoaresdev.gestao_vagas.modules.candidate.controllers;

import br.com.lucassoaresdev.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    private ProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
        try {
            CandidateEntity response =  this.createCandidateUseCase.execute(candidate);
            return ResponseEntity.accepted().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request) {
        try {
            Object id = request.getAttribute("candidate_id");

            ProfileCandidateResponseDTO response =  this.profileCandidateUseCase.execute(UUID.fromString(id.toString()));

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
