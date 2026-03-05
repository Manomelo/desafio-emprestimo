package desafio.backend.emprestimo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmprestimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar 200 com empréstimo consignado para salário alto")
    void deveRetornar200ComConsignadoParaSalarioAlto() throws Exception {
        String body = """
                {
                    "age": 40,
                    "cpf": "12345678901",
                    "name": "João Silva",
                    "income": 7000.00,
                    "location": "SP"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").value("João Silva"))
                .andExpect(jsonPath("$.loans[?(@.type=='CONSIGNADO')].interest_rate").value(2));
    }

    @Test
    @DisplayName("Deve retornar empréstimo pessoal e com garantia para salário baixo")
    void deveRetornarPessoalEGarantiaParaSalarioBaixo() throws Exception {
        String body = """
                {
                    "age": 35,
                    "cpf": "12345678901",
                    "name": "Maria Souza",
                    "income": 2000.00,
                    "location": "MG"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loans[?(@.type=='PESSOAL')].interest_rate").value(4))
                .andExpect(jsonPath("$.loans[?(@.type=='GARANTIA')].interest_rate").value(3));
    }

    @Test
    @DisplayName("Deve retornar pessoal e garantia para jovem em SP com salário médio")
    void deveRetornarPessoalEGarantiaParaJovemSPSalarioMedio() throws Exception {
        String body = """
                {
                    "age": 25,
                    "cpf": "12345678901",
                    "name": "Ana Lima",
                    "income": 4000.00,
                    "location": "SP"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loans[?(@.type=='PESSOAL')]").exists())
                .andExpect(jsonPath("$.loans[?(@.type=='GARANTIA')]").exists())
                .andExpect(jsonPath("$.loans[?(@.type=='CONSIGNADO')]").doesNotExist());
    }

    @Test
    @DisplayName("Não deve conceder nenhum empréstimo para salário médio, idade >= 30, fora de SP")
    void naoDeveConcederEmprestimoParaSalarioMedioIdadeAltaForaSP() throws Exception {
        String body = """
                {
                    "age": 35,
                    "cpf": "12345678901",
                    "name": "Carlos Rocha",
                    "income": 4000.00,
                    "location": "RJ"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loans").isEmpty());
    }

    @Test
    @DisplayName("Deve retornar 400 quando campos obrigatórios estão ausentes")
    void deveRetornar400QuandoCamposObrigatoriosAusentes() throws Exception {
        String body = """
                {
                    "name": "Incompleto"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve retornar o nome correto do cliente na resposta")
    void deveRetornarNomeCorretoDoCliente() throws Exception {
        String body = """
                {
                    "age": 26,
                    "cpf": "12345678901",
                    "name": "Vuxaywua Zukiagou",
                    "income": 7000.00,
                    "location": "SP"
                }
                """;

        mockMvc.perform(post("/customer-loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").value("Vuxaywua Zukiagou"));
    }
}