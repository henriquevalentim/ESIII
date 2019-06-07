package br.edu.fatec.aula.dominio;

import java.util.Date;

public class EntidadeDominio {

	protected int id;
	private Date dtCadastro;
	
	
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void complementarDtCadastro(){
		dtCadastro = new Date();
	}
	
	
}
