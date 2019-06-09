package br.edu.fatec.aula.negocio;

import br.edu.fatec.aula.dominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
}
