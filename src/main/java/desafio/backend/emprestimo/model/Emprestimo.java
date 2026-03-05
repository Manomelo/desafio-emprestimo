package desafio.backend.emprestimo.model;

import desafio.backend.emprestimo.enums.TipoEmprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Modelo de dominio que representa um emprestimo com tipo e taxa de juros.
 */
@Getter
@AllArgsConstructor
public class Emprestimo {
    /** Tipo do emprestimo concedido. */
    private TipoEmprestimo tipoEmprestimo;

    /** Taxa de juros aplicada ao emprestimo. */
    private double interestRate;

}
