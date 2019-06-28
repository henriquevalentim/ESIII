package br.edu.fatec.aula.strategy;

import br.edu.fatec.aula.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
