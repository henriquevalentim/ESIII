package br.edu.fatec.aula.dominio;

public class Regional extends EntidadeDominio{
	
	private String nome;
	
	public Regional(String nome) {
		super();
		this.nome = nome;
	}
	
	public Regional() {
		super();
	}
	
	public String validarRegional() {
		StringBuilder sb = new StringBuilder();

		if (nome == null || nome.trim().equals("")) {
			sb.append("NOME DA REGIÃO É UM CAMPO OBRIGATÓRIO!\n");
		}

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}

	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
