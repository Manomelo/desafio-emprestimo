package desafio.backend.emprestimo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import desafio.backend.emprestimo.enums.TipoEmprestimo;

public record EmprestimoDTO(
        TipoEmprestimo type,

        @JsonProperty("interest_rate")
        double interestRate) {

}
