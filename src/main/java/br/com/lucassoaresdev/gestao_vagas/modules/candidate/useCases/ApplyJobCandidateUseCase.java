package br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.*;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private ApplyJobRepository applyJobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;


    public ApplyJobEntity execute(UUID candidateId, UUID jobId) {
        this.candidateRepository.findById(candidateId).orElseThrow(UserNotFoundException::new);
        this.jobRepository.findById(jobId).orElseThrow(JobNotFoundException::new);

        ApplyJobEntity applyJobEntity = new ApplyJobEntity();
        applyJobEntity.setCandidateId(candidateId);
        applyJobEntity.setJobId(jobId);

        return this.applyJobRepository.save(applyJobEntity);
    }
}
