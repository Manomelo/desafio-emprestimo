package desafio.backend.emprestimo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import desafio.backend.emprestimo.dto.EmprestimoDTO;
import desafio.backend.emprestimo.dto.ResponseDTO;
import desafio.backend.emprestimo.dto.UsuarioRequestDTO;
import desafio.backend.emprestimo.service.EmprestimoService;
import jakarta.validation.Valid;

/**
 * Controlador REST responsavel por expor o endpoint de simulacao de emprestimos.
 */
@RestController
@RequestMapping("/customer-loans")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    /**
     * Cria o controlador com a regra de negocio de emprestimos.
     *
     * @param emprestimoService servico que calcula os emprestimos elegiveis
     */
    public EmprestimoController(EmprestimoService emprestimoService){
        this.emprestimoService = emprestimoService;
    }
    
    /**
     * Calcula os emprestimos disponiveis para um cliente.
     *
     * @param request dados do cliente para avaliacao
     * @return resposta com nome do cliente e lista de emprestimos elegiveis
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> gerarEmprestimo(@Valid @RequestBody UsuarioRequestDTO request) {
        ResponseDTO response = new ResponseDTO(request.getName(), emprestimoService.gerarEmprestimos(request));
        return ResponseEntity.ok(response);
    }
}
