package br.com.mbalujunior.gestao_vagas.modules.company.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.mbalujunior.gestao_vagas.modules.company.repositories.CompanyRepository;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
    }
/* @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        // Criar uma empresa para o teste
        var company = CompanyEntity.builder()
            .description("COMPANY_DESCRIPTION")
            .email("email@company.com")
            .password("1234567890")
            .username("COMPANY_USERNAME")
            .name("COMPANY_NAME")
            .build();

        // Salvar empresa no repositório
        company = companyRepository.saveAndFlush(company);

        // Criar o DTO para o novo trabalho
        var createJobDTO = CreateJobDTO.builder()
            .benefits("BENEFITS_TEST")
            .description("DESCRIPTION_TEST")
            .level("LEVEL_TEST")
            .build();

        // Enviar requisição para criar o trabalho
        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO)) // Converte o DTO para JSON
                .header("Authorization", TestUtils.generateToken(company.getId(), "JAVAGAS_@123#"))) // Gera token com ID da empresa
            .andExpect(MockMvcResultMatchers.status().isOk()) // Verifica que o status é OK
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()); // Verifica se o ID do trabalho é retornado

        // Imprime o resultado no console (opcional)
        System.out.println("Job created successfully");
    }


   
 @Test
public void should_not_be_able_to_create_a_job_if_company_not_found() throws Exception {
    // Criar o DTO para o novo trabalho
    var createJobDTO = CreateJobDTO.builder()
        .benefits("BENEFITS_TEST")
        .description("DESCRIPTION_TEST")
        .level("LEVEL_TEST")
        .build();

    // Enviar requisição com um token inválido
    mvc.perform(MockMvcRequestBuilders.post("/company/job/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtils.objectToJson(createJobDTO)) // Converte o DTO para JSON
            .header("Authorization", TestUtils.generateToken(UUID.randomUUID(), "JAVAGAS_@123#"))) // Gera token com UUID aleatório
        .andExpect(MockMvcResultMatchers.status().isBadRequest()) // Verifica que o status é BadRequest
        .andExpect(MockMvcResultMatchers.content().string("Company not found")); // Verifica se a mensagem de erro é uma string
}
 */
}
