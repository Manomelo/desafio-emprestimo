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

@RestController
@RequestMapping("/customer-loans")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService){
        this.emprestimoService = emprestimoService;
    }
    
    @PostMapping
    public ResponseEntity<ResponseDTO> gerarEmprestimo(@Valid @RequestBody UsuarioRequestDTO request) {
        ResponseDTO response = new ResponseDTO(request.getName(), emprestimoService.gerarEmprestimos(request));
        return ResponseEntity.ok(response);
    }
}
