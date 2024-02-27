package br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.UserFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(CandidateEntity candidate){
        this.candidateRepository
                .findByUsernameOrEmail(candidate.getUsername(), candidate.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        candidate.setPassword(this.passwordEncoder.encode(candidate.getPassword()));

        return this.candidateRepository.save(candidate);
    }
}
