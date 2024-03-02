package br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto;

import java.util.Date;

public record AuthCandidateResponseDTO(String access_token, long expires_in) {
}
