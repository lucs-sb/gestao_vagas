package br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCandidateRequestDTO(@Schema(example = "lucs", requiredMode = Schema.RequiredMode.REQUIRED) String username,
                                      @Schema(example = "lucs1234", requiredMode = Schema.RequiredMode.REQUIRED) String password) {
}
