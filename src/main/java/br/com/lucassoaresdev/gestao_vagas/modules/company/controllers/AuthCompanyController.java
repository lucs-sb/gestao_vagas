package br.com.lucassoaresdev.gestao_vagas.modules.company.controllers;

import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.AuthCompanyRequestDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.useCases.AuthCompanyUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
@Tag(name = "Empresa", description = "Informações da empresa")
public class AuthCompanyController {

    @Autowired
    private AuthCompanyUseCase authCompanyUseCase;

    @PostMapping("/auth")
    @Operation(summary = "Autenticação da empresa", description = "Essa função é responsável por criar um token de autenticação para uma empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthCompanyResponseDTO.class))}),
            @ApiResponse(responseCode = "401", description = "Username/password incorrect")
    })
    public ResponseEntity<Object> create(AuthCompanyRequestDTO authCompanyRequestDTO) {
        try {
            AuthCompanyResponseDTO response = authCompanyUseCase.execute(authCompanyRequestDTO);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
