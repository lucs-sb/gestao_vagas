package br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

public record ProfileCandidateResponseDTO(UUID id, String username, String email, String name, String description) {
}
