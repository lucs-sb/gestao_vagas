package br.com.lucassoaresdev.gestao_vagas.modules.company.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.JobEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity job) {
        companyRepository.findById(job.getCompanyId()).orElseThrow(CompanyNotFoundException::new);

        return this.jobRepository.save(job);
    }
}
