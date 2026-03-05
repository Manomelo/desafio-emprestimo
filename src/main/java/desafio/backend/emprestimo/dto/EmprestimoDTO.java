package desafio.backend.emprestimo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import desafio.backend.emprestimo.enums.TipoEmprestimo;

/**
 * DTO que representa um emprestimo disponivel para o cliente.
 *
 * @param type tipo do emprestimo
 * @param interestRate taxa de juros aplicada
 */
public record EmprestimoDTO(
        TipoEmprestimo type,

        @JsonProperty("interest_rate")
        double interestRate) {

}
