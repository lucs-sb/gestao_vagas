package br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID id) {
        Optional<CandidateEntity> candidate = Optional.ofNullable(this.candidateRepository.findById(id).orElseThrow(UserNotFoundException::new));

        return new ProfileCandidateResponseDTO(
                candidate.get().getId(),
                candidate.get().getUsername(),
                candidate.get().getEmail(),
                candidate.get().getName(),
                candidate.get().getDescription()
        );
    }
}
