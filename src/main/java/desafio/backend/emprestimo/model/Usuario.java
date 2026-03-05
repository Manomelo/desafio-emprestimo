package desafio.backend.emprestimo.model;

import desafio.backend.emprestimo.enums.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Modelo de dominio com os dados cadastrais e financeiros do usuario.
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
