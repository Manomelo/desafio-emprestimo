package desafio.backend.emprestimo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import desafio.backend.emprestimo.dto.EmprestimoDTO;
import desafio.backend.emprestimo.dto.UsuarioRequestDTO;
import desafio.backend.emprestimo.enums.Estado;
import desafio.backend.emprestimo.enums.TipoEmprestimo;
import desafio.backend.emprestimo.service.EmprestimoService;

class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;

    @BeforeEach
    void setUp() {
        emprestimoService = new EmprestimoService();
    }

    // -------------------------------------------------------
    // Empréstimo Pessoal
    // -------------------------------------------------------

    @Test
    @DisplayName("Deve conceder empréstimo pessoal quando salário <= 3000")
    void deveConcederEmprestimoPessoalQuandoSalarioBaixo() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "João", 2000, Estado.RJ);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertContainsTipo(result, TipoEmprestimo.PESSOAL);
    }

    @Test
    @DisplayName("Deve conceder empréstimo pessoal quando salário entre 3000 e 5000, idade < 30 e SP")
    void deveConcederEmprestimoPessoalQuandoSalarioMedioJovemSP() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Maria", 4000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertContainsTipo(result, TipoEmprestimo.PESSOAL);
    }

    @Test
    @DisplayName("Não deve conceder empréstimo pessoal quando salário entre 3000 e 5000 e idade >= 30")
    void naoDeveConcederEmprestimoPessoalQuandoIdadeMaior30() {
        var usuario = new UsuarioRequestDTO(30, "12345678901", "Carlos", 4000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertNotContainsTipo(result, TipoEmprestimo.PESSOAL);
    }

    @Test
    @DisplayName("Não deve conceder empréstimo pessoal quando salário entre 3000 e 5000 e localização != SP")
    void naoDeveConcederEmprestimoPessoalQuandoLocalizacaoForaDeSP() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Ana", 4000, Estado.RJ);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertNotContainsTipo(result, TipoEmprestimo.PESSOAL);
    }

    @Test
    @DisplayName("Não deve conceder empréstimo pessoal quando salário >= 5000")
    void naoDeveConcederEmprestimoPessoalQuandoSalarioAlto() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Pedro", 5000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertNotContainsTipo(result, TipoEmprestimo.PESSOAL);
    }

    // -------------------------------------------------------
    // Empréstimo com Garantia
    // -------------------------------------------------------

    @Test
    @DisplayName("Deve conceder empréstimo com garantia quando salário <= 3000")
    void deveConcederEmprestimoGarantiaQuandoSalarioBaixo() {
        var usuario = new UsuarioRequestDTO(35, "12345678901", "Lucas", 1500, Estado.MG);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertContainsTipo(result, TipoEmprestimo.GARANTIA);
    }

    @Test
    @DisplayName("Deve conceder empréstimo com garantia quando salário entre 3000 e 5000, idade < 30 e SP")
    void deveConcederEmprestimoGarantiaQuandoSalarioMedioJovemSP() {
        var usuario = new UsuarioRequestDTO(29, "12345678901", "Fernanda", 4000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertContainsTipo(result, TipoEmprestimo.GARANTIA);
    }

    @Test
    @DisplayName("Não deve conceder empréstimo com garantia quando salário >= 5000")
    void naoDeveConcederEmprestimoGarantiaQuandoSalarioAlto() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Rafael", 6000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertNotContainsTipo(result, TipoEmprestimo.GARANTIA);
    }

    // -------------------------------------------------------
    // Empréstimo Consignado
    // -------------------------------------------------------

    @Test
    @DisplayName("Deve conceder empréstimo consignado quando salário >= 5000")
    void deveConcederEmprestimoConsignadoQuandoSalarioAlto() {
        var usuario = new UsuarioRequestDTO(40, "12345678901", "Beatriz", 5000, Estado.RS);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertContainsTipo(result, TipoEmprestimo.CONSIGNADO);
    }

    @Test
    @DisplayName("Não deve conceder empréstimo consignado quando salário < 5000")
    void naoDeveConcederEmprestimoConsignadoQuandoSalarioBaixo() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Gustavo", 4999, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        assertNotContainsTipo(result, TipoEmprestimo.CONSIGNADO);
    }

    // -------------------------------------------------------
    // Taxas de juros
    // -------------------------------------------------------

    @Test
    @DisplayName("Empréstimo pessoal deve ter taxa de 4%")
    void emprestimoPessoalDeveTerTaxa4() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "João", 2000, Estado.RJ);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        result.stream()
                .filter(e -> e.type() == TipoEmprestimo.PESSOAL)
                .findFirst()
                .ifPresent(e -> assertThat(e.interestRate()).isEqualTo(4));
    }

    @Test
    @DisplayName("Empréstimo consignado deve ter taxa de 2%")
    void emprestimoConsignadoDeveTerTaxa2() {
        var usuario = new UsuarioRequestDTO(40, "12345678901", "Ana", 6000, Estado.SP);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        result.stream()
                .filter(e -> e.type() == TipoEmprestimo.CONSIGNADO)
                .findFirst()
                .ifPresent(e -> assertThat(e.interestRate()).isEqualTo(2));
    }

    @Test
    @DisplayName("Empréstimo com garantia deve ter taxa de 3%")
    void emprestimoGarantiaDeveTerTaxa3() {
        var usuario = new UsuarioRequestDTO(25, "12345678901", "Maria", 2000, Estado.RJ);
        Set<EmprestimoDTO> result = emprestimoService.gerarEmprestimos(usuario);

        result.stream()
                .filter(e -> e.type() == TipoEmprestimo.GARANTIA)
                .findFirst()
                .ifPresent(e -> assertThat(e.interestRate()).isEqualTo(3));
    }

    // -------------------------------------------------------
    // Helpers
    // -------------------------------------------------------

    private void assertContainsTipo(Set<EmprestimoDTO> emprestimos, TipoEmprestimo tipo) {
        assertThat(emprestimos).extracting(EmprestimoDTO::type).contains(tipo);
    }

    private void assertNotContainsTipo(Set<EmprestimoDTO> emprestimos, TipoEmprestimo tipo) {
        assertThat(emprestimos).extracting(EmprestimoDTO::type).doesNotContain(tipo);
    }
}