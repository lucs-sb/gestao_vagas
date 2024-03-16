package br.com.lucassoaresdev.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthCompanyRequestDTO(@Schema(example = "expert", requiredMode = Schema.RequiredMode.REQUIRED) String username,
                                    @Schema(example = "expert1234", requiredMode = Schema.RequiredMode.REQUIRED) String password) {
}
