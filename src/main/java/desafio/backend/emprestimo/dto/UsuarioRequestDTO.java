package desafio.backend.emprestimo.dto;

import desafio.backend.emprestimo.enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioRequestDTO {

    @NotNull(message = "Age nao pode ser nula")
    @Positive(message = "Age nao pode ser negativa")
    private int age;

    @NotBlank(message = "CPF nao pode ser vazio")
    @Size(min = 11, max = 11, message = "CPF deve conter 11 digitos")
    private String cpf;

    @NotNull(message = "Name nao pode ser vazio")
    @Size(max = 50, message = "Name deve ter no maximo 50 caracteres")
    private String name;

    @Positive(message = "Income nao pode ser negativa")
    @NotNull(message = "Income nao pode ser nula")
    private double income;

    @NotNull(message = "location nao pode ser vazio")
    private Estado location;
}
	


