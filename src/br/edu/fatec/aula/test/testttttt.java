package br.edu.fatec.aula.test;

import br.edu.fatec.aula.dao.UsuarioDAO;
import br.edu.fatec.aula.dominio.Usuario;

public class testttttt {

	public static void main(String[] args) {

		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setSenha("123456789");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuairoA = new Usuario();
		usuairoA = (Usuario)usuarioDAO.autenticar(usuario);
		System.out.println(usuairoA.getLogin());
	}

}
