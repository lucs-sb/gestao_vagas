package br.com.lucassoaresdev.gestao_vagas.modules.company.controllers;

import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> create(AuthCompanyDTO authCompanyDTO) {
        try {
            String jwt = authCompanyUseCase.execute(authCompanyDTO);
            return ResponseEntity.ok().body(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
