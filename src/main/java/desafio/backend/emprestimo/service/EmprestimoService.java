package desafio.backend.emprestimo.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import desafio.backend.emprestimo.dto.EmprestimoDTO;
import desafio.backend.emprestimo.dto.UsuarioRequestDTO;
import desafio.backend.emprestimo.enums.Estado;
import desafio.backend.emprestimo.enums.TipoEmprestimo;

/**
 * Servico com as regras de elegibilidade para concessao de emprestimos.
 */
@Service
public class EmprestimoService {
    
    /**
     * Gera os emprestimos elegiveis para um cliente de acordo com renda, idade e estado.
     *
     * @param usuario dados do cliente usados na avaliacao
     * @return conjunto de emprestimos que o cliente pode contratar
     */
    public Set<EmprestimoDTO> gerarEmprestimos(UsuarioRequestDTO usuario){
        Set<EmprestimoDTO> emprestimos = new HashSet<>();
        EmprestimoDTO emprestimoPessoal = new EmprestimoDTO(TipoEmprestimo.PESSOAL,4 );
        EmprestimoDTO emprestimoGarantido = new EmprestimoDTO(TipoEmprestimo.GARANTIA, 3);
        EmprestimoDTO emprestimoConsignado = new EmprestimoDTO(TipoEmprestimo.CONSIGNADO, 2);

        // 1. Conceder o empréstimo pessoal se o salário do cliente for igual ou inferior a R$ 3000.
        // 2. Conceder o empréstimo com garantia se o salário do cliente for igual ou inferior a R$ 3000.
        if(usuario.getIncome() <= 3000){
            emprestimos.add(emprestimoPessoal);
            emprestimos.add(emprestimoGarantido);
        }

        // 3. Conceder o empréstimo pessoal se o salário do cliente estiver entre R$ 3000 e R$ 5000, 
        // se o cliente tiver menos de 30 anos e residir em São Paulo (SP).
        // 4. Conceder o empréstimo com garantia se o salário do cliente estiver entre R$ 3000 e R$ 5000, 
        // se o cliente tiver menos de 30 anos e residir em São Paulo (SP).
        if((usuario.getIncome() >= 3000 && usuario.getIncome() <= 5000) && 
            usuario.getAge() < 30 && 
            usuario.getLocation() == Estado.SP){
                emprestimos.add(emprestimoPessoal);
                emprestimos.add(emprestimoGarantido);
        }

        // 5. Conceder o empréstimo consignado se o salário do cliente for igual ou superior a R$ 5000.
        if(usuario.getIncome() >= 5000){
            emprestimos.add(emprestimoConsignado);
        }

        return emprestimos;
    }
    
}
