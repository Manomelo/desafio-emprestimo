package desafio.backend.emprestimo.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ResponseDTO(
    String customer,
    @JsonProperty("loans")
    Set<EmprestimoDTO> emprestimos
) {}


