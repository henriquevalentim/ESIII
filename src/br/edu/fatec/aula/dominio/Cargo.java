package br.edu.fatec.aula.dominio;

public class Cargo extends EntidadeDominio {

	private String descricao;

	public Cargo() {
		super();
	}

	public Cargo(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String validarCargo() {
		StringBuilder sb = new StringBuilder();

		if (descricao == null || descricao.trim().equals("")) {
			sb.append("DESCRIÇÃO DO CARGO É UM CAMPO OBRIGATÓRIO!\n");
		}

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}

	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
