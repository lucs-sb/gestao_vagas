package br.com.lucassoaresdev.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCompanyResponseDTO(@Schema(example = "gfffffffdfhzrhrhtr") String access_token,
                                     @Schema(example = "2000") long expires_in) {
}
