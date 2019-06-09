package br.edu.fatec.aula.negocio;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class ValidarDadosFuncionario extends AbstractValidador {

	@Override
	public String processar(EntidadeDominio entidade) {

		Funcionario funcionario = (Funcionario) entidade;

		if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
			sb.append("NOME � UM CAMPO OBRIGAT�RIO!\n");
		}

		if (funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")) {
			sb.append("CPF � UM CAMPO OBRIGAT�RIO!\n");
		}

		if (funcionario.getEmail() == null || funcionario.getEmail().trim().equals("")) {
			sb.append("EMAIL � UM CAMPO OBRIGAT�RIO!\n");
		}

		if (funcionario.getMatricula() == null || funcionario.getMatricula().trim().equals("")) {
			sb.append("MATRICULA � UM CAMPO OBRIGAT�RIO!\n");
		}

		// validar as outras classes aki(Cargo,Regional,etc...)
		//importante validar apenas as existencias dos elementos
		//valida�oes para verificar se o valor � valido deve ser feito
		//depois para separar melhor a responsabilidade do codigo

		return verificaMsg();

	}

}
