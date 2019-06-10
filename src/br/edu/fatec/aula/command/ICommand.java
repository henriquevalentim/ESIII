package br.edu.fatec.aula.command;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Resultado;

public interface ICommand {

	public Resultado executar(EntidadeDominio entidade);
	
}
