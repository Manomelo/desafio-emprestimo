package desafio.backend.emprestimo.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO de resposta da API de emprestimos.
 *
 * @param customer nome do cliente consultado
 * @param emprestimos emprestimos elegiveis retornados pela analise
 */
public record ResponseDTO(
    String customer,
    @JsonProperty("loans")
    Set<EmprestimoDTO> emprestimos
) {}


