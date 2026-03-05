package desafio.backend.emprestimo.dto;

import desafio.backend.emprestimo.enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO de entrada com os dados necessarios para avaliar emprestimos do cliente.
 */
@Getter
@AllArgsConstructor
public class UsuarioRequestDTO {

    /** Idade do cliente. */
    @NotNull(message = "Age nao pode ser nula")
    @Positive(message = "Age nao pode ser negativa")
    private int age;

    /** CPF do cliente no formato esperado pela API. */
    @NotBlank(message = "CPF nao pode ser vazio")
    @Size(min = 14, max = 14, message = "CPF deve conter 11 digitos")
    private String cpf;

    /** Nome completo do cliente. */
    @NotNull(message = "Name nao pode ser vazio")
    @Size(max = 50, message = "Name deve ter no maximo 50 caracteres")
    private String name;

    /** Renda mensal do cliente. */
    @Positive(message = "Income nao pode ser negativa")
    @NotNull(message = "Income nao pode ser nula")
    private double income;

    /** UF de residencia usada nas regras de elegibilidade. */
    @NotNull(message = "location nao pode ser vazio")
    private Estado location;
}
	


