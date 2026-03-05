package desafio.backend.emprestimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da aplicacao Spring Boot para calculo de emprestimos.
 */
@SpringBootApplication
public class EmprestimoApplication {

	/**
	 * Inicializa o contexto da aplicacao.
	 *
	 * @param args argumentos de linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmprestimoApplication.class, args);
	}

}
