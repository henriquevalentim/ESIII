package br.edu.fatec.aula.strategy;

import java.util.Date;

import br.edu.fatec.aula.dominio.EntidadeDominio;

public class CompletarDtCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {

		entidade.setDtCadastro(new Date());
		return null;
	}

}
