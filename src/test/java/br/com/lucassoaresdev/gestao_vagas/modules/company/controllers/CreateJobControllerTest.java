package br.com.lucassoaresdev.gestao_vagas.modules.company.controllers;

import br.com.lucassoaresdev.gestao_vagas.modules.company.dto.CreateJobRequestDTO;
import br.com.lucassoaresdev.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.lucassoaresdev.gestao_vagas.modules.company.repositories.CompanyRepository;
import br.com.lucassoaresdev.gestao_vagas.utils.UtilsTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {

        CompanyEntity company = new CompanyEntity();
        company.setName("COMPANY_NAME");
        company.setEmail("email@company.com");
        company.setUsername("COMPANY_USERNAME");
        company.setDescription("COMPANY_DESCRIPTION");
        company.setPassword("1234567890");

        company = companyRepository.saveAndFlush(company);

        CreateJobRequestDTO job = new CreateJobRequestDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilsTest.objectToJSON(job))
                        .header("Authorization", UtilsTest.generateToken(company.getId())))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void should_not_be_able_to_create_a_new_job_if_company_not_found() throws Exception {
        CreateJobRequestDTO job = new CreateJobRequestDTO("DESCRIPTION_TEST", "BENEFITS_TEST", "LEVEL_TEST");

        mvc.perform(MockMvcRequestBuilders.post("/company/job")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilsTest.objectToJSON(job))
                        .header("Authorization", UtilsTest.generateToken(UUID.randomUUID())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
