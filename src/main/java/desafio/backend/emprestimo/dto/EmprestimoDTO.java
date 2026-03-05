package desafio.backend.emprestimo.dto;

import desafio.backend.emprestimo.enums.TipoEmprestimo;

public record EmprestimoDTO(
        TipoEmprestimo type,
        double interestRate) {

}
