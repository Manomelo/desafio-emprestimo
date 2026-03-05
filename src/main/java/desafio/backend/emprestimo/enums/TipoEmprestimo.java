package desafio.backend.emprestimo.enums;

/**
 * Tipos de emprestimo oferecidos pela aplicacao.
 */
public enum TipoEmprestimo {
    /** Emprestimo pessoal sem necessidade de garantia real. */
    PESSOAL,

    /** Emprestimo consignado com desconto em folha. */
    CONSIGNADO,

    /** Emprestimo com garantia de um bem do cliente. */
    GARANTIA
}
