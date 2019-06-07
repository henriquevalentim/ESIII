package br.edu.fatec.aula.dominio;

public class Setor extends EntidadeDominio{
	
	private String nome;

	public Setor(String nome) {
		super();
		this.nome = nome;
	}
	public Setor() {
		super();
	}

	public String validarSetor() {
		StringBuilder sb = new StringBuilder();

		if (nome == null || nome.trim().equals("")) {
			sb.append("NOME DO SETOR É UM CAMPO OBRIGATÓRIO!\n");
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
