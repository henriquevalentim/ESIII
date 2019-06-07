package br.edu.fatec.aula.dominio;

public class PerfilAtendimento extends EntidadeDominio {

	private String tipoPerfil;

	public PerfilAtendimento(String tipoPerfil) {
		super();
		this.tipoPerfil = tipoPerfil;
	}

	public PerfilAtendimento() {
		super();
	}

	public String getTipoPerfil() {
		return tipoPerfil;
	}

	public void setTipoPerfil(String tipoPerfil) {
		this.tipoPerfil = tipoPerfil;
	}
	
	public String validarPerfilAtendimento() {
		StringBuilder sb = new StringBuilder();

		if (tipoPerfil == null || tipoPerfil.trim().equals("")) {
			sb.append("TIPO PERFIL É UM CAMPO OBRIGATÓRIO!\n");
		}
		
		if (!tipoPerfil.toLowerCase().equals("atendente") && 
				!tipoPerfil.toLowerCase().equals("triagem inicial") &&
				!tipoPerfil.toLowerCase().equals("triagem de grupo") &&
				!tipoPerfil.toLowerCase().equals("administrador") &&
				!tipoPerfil.toLowerCase().equals("administrador de sistema")) {
			sb.append("TIPO PERFIL NÃO ENCONTRADO!\n");
		}

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}

	}

}
