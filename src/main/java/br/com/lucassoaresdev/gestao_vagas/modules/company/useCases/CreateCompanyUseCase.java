package br.com.lucassoaresdev.gestao_vagas.modules.company.useCases;

import br.com.lucassoaresdev.gestao_vagas.exceptions.UserFoundException;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    public void execute(CompanyEntity company) {

        this.companyRepository
                .findByUsernameOrEmail(company.getUsername(), company.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        this.companyRepository.save(company);
    }
}
