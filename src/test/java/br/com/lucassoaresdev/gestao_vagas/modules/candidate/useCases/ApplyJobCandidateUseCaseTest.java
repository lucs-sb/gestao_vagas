package br.com.lucassoaresdev.gestao_vagas.modules.candidate.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.JobNotFoundException;
import br.com.lucassoaresdev.gestao_vagas.exceptions.UserNotFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.entity.ApplyJobEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.entity.CandidateEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.candidate.repository.CandidateRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.JobRepository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        try {
            UUID candidateId = UUID.randomUUID();

            CandidateEntity candidate = new CandidateEntity();
            candidate.setId(candidateId);

            when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

            applyJobCandidateUseCase.execute(candidateId, null);

        } catch (Exception ex) {
            assertThat(ex).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should be able to create a new apply job")
    public void should_be_able_to_create_a_new_apply_job() {

        UUID candidateId = UUID.randomUUID();
        UUID jobId = UUID.randomUUID();

        ApplyJobEntity applyJobCreated = new ApplyJobEntity();
        applyJobCreated.setId(UUID.randomUUID());

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(any())).thenReturn(applyJobCreated);

        ApplyJobEntity result = applyJobCandidateUseCase.execute(candidateId, jobId);

        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}
