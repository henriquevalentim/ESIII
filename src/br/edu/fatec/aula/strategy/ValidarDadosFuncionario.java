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
			sb.append("NOME � UM CAMPO OBRIGAT�RIO!<br>");
		}

		if (funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")) {
			sb.append("CPF � UM CAMPO OBRIGAT�RIO!<br>");
		}

		if (funcionario.getEmail() == null || funcionario.getEmail().trim().equals("")) {
			sb.append("EMAIL � UM CAMPO OBRIGAT�RIO!<br>");
		}

		if (funcionario.getMatricula() == null || funcionario.getMatricula().trim().equals("")) {
			sb.append("MATRICULA � UM CAMPO OBRIGAT�RIO!<br>");
		}
		
		if (funcionario.getSenha() == null || funcionario.getSenha().trim().equals("")) {
			sb.append("SENHA � UM CAMPO OBRIGAT�RIO!<br>");
		}
		
		if (funcionario.getCargo().getId() == 0) {
			sb.append("CARGO � UM CAMPO OBRIGAT�RIO!<br>");
		}
		
		if (funcionario.getSetor().getId() == 0) {
			sb.append("SETOR � UM CAMPO OBRIGAT�RIO!<br>");
		}
		
		if (funcionario.getRegional().getId() == 0) {
			sb.append("REGIONAL � UM CAMPO OBRIGAT�RIO!<br>");
		}
		

		return sb.toString();

	}

}
