package desafio.backend.emprestimo.model;

import desafio.backend.emprestimo.enums.TipoEmprestimo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Emprestimo {
    private TipoEmprestimo tipoEmprestimo;
    private double interestRate;

}
