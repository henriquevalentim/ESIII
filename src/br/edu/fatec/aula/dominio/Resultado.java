package br.edu.fatec.aula.dominio;

import java.util.ArrayList;
import java.util.List;

public class Resultado {

	private String msg;
	private List<EntidadeDominio> entidades;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	
	public List<EntidadeDominio> addEntidades(EntidadeDominio entidade) {
		if(entidades == null) {
			entidades = new ArrayList<EntidadeDominio>();
			entidades.add(entidade);
		}
		else
			entidades.add(entidade);
		
		return entidades;
		
	}

}
