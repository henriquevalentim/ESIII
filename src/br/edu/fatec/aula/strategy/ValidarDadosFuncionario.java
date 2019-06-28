package br.edu.fatec.aula.strategy;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class ValidarDadosFuncionario implements IStrategy {

	private StringBuilder sb = null;
	
	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Funcionario funcionario = (Funcionario) entidade;

		if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
			sb.append("NOME É UM CAMPO OBRIGATÓRIO!<br>");
		}

		if (funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")) {
			sb.append("CPF É UM CAMPO OBRIGATÓRIO!<br>");
		}

		if (funcionario.getEmail() == null || funcionario.getEmail().trim().equals("")) {
			sb.append("EMAIL É UM CAMPO OBRIGATÓRIO!<br>");
		}

		if (funcionario.getMatricula() == null || funcionario.getMatricula().trim().equals("")) {
			sb.append("MATRICULA É UM CAMPO OBRIGATÓRIO!<br>");
		}
		
		if (funcionario.getSenha() == null || funcionario.getSenha().trim().equals("")) {
			sb.append("SENHA É UM CAMPO OBRIGATÓRIO!<br>");
		}
		
		if (funcionario.getCargo().getId() == 0) {
			sb.append("CARGO É UM CAMPO OBRIGATÓRIO!<br>");
		}
		
		if (funcionario.getSetor().getId() == 0) {
			sb.append("SETOR É UM CAMPO OBRIGATÓRIO!<br>");
		}
		
		if (funcionario.getRegional().getId() == 0) {
			sb.append("REGIONAL É UM CAMPO OBRIGATÓRIO!<br>");
		}
		

		return sb.toString();

	}

}
