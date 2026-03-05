package desafio.backend.emprestimo.dto;

import desafio.backend.emprestimo.enums.TipoEmprestimo;

public record ResponseDTO(
    String customer,
    TipoEmprestimo type,
    double interestRate
) {}


