package br.edu.fatec.aula.dominio;

public class Usuario extends EntidadeDominio {
	private String login;
	private String senha;
	private PerfilAtendimento perfilAtendimento;

	public Usuario(String login, String senha, PerfilAtendimento perfilAtendimento) {
		super();
		this.login = login;
		this.senha = senha;
		this.perfilAtendimento = perfilAtendimento;
	}

	public Usuario() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public PerfilAtendimento getPerfilAtendimento() {
		return perfilAtendimento;
	}

	public void setPerfilAtendimento(PerfilAtendimento perfilAtendimento) {
		this.perfilAtendimento = perfilAtendimento;
	}

	public String validarUsuario() {
		StringBuilder sb = new StringBuilder();

		if (login == null || login.trim().equals("")) {
			sb.append("LOGIN É UM CAMPO OBRIGATÓRIO!\n");
		}
		
		if (senha == null || senha.trim().equals("")) {
			sb.append("SENHA É UM CAMPO OBRIGATÓRIO!\n");
		}
		
		if (senha.length() < 8) {
			sb.append("SENHA DEVE SER COMPOSTA DE PELO MENOS 8 CARACTERES!\n");
		}

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}

	}

	
}
