package br.edu.fatec.aula.negocio;

import br.edu.fatec.aula.dao.FuncionarioDAO;
import br.edu.fatec.aula.dao.IDAO;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class ValidarExistencia implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		Funcionario funcionario = (Funcionario) entidade;
		IDAO dao = new FuncionarioDAO();
		
		if(dao.consultar(funcionario).size() != 0) {
			return "Cliente ja cadastrado!";
		}
		
		return null;
	}

}
