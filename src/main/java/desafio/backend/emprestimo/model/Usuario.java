package desafio.backend.emprestimo.model;

import desafio.backend.emprestimo.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de dados do usuario para o calculo do emprestimo
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private int age;
    private String cpf;
    private String name;
    private double income;
    private Estado location;
}
