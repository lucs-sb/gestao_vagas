package br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ProfileCandidateResponseDTO(UUID id,
                                          @Schema(example = "lucs")
                                          String username,
                                          @Schema(example = "lucas@gmail.com")
                                          String email,
                                          @Schema(example = "Lucas Soares")
                                          String name,
                                          @Schema(example = "Desenvolvedor Java")
                                          String description) {
}
