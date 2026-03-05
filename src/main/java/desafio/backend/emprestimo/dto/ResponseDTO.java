package desafio.backend.emprestimo.dto;

import desafio.backend.emprestimo.enums.TipoEmprestimo;

public record ResponseDTO(
    TipoEmprestimo type,
    double interestRate
) {}


