package br.edu.fatec.aula.command;

import br.edu.fatec.aula.web.fachada.Fachada;
import br.edu.fatec.aula.web.fachada.IFachada;

public abstract class AbstractCommand implements ICommand{
	
	protected IFachada fachada = new Fachada();

}
