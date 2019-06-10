package br.edu.fatec.aula.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.Usuario;

public class Filtro {

	private String querry;
	private Map<String, String> mapNomeTabela;
	private Map<Integer, String> mapFiltroQuerry;
	private List<Integer> listQuerryFuncionario;
	private List<Integer> listQuerryUsuario;
	// private List<Integer> listQuerryEndereco;

	// private boolean flgEndereco = false;
	private boolean flgUsuario = false;
	private boolean flgFuncionario = false;

	public Filtro() {

		mapNomeTabela = new HashMap<String, String>();
		mapNomeTabela.put(Funcionario.class.getName(), "tb_funcionario");
		mapNomeTabela.put(Usuario.class.getName(), "tb_usuario");

	}

	public String gerarQuerry(Funcionario funcionario) {

		querry = "SELECT * FROM tb_funcionario as F";

		mapFiltroQuerry = new HashMap<Integer, String>();

		String selectUsuario = null;

		if (funcionario.getUsuario() != null)
			selectUsuario = querryUsuario(funcionario.getUsuario());

		if (flgUsuario)
			querry += selectUsuario;
		else
			querry += ", tb_usuario as U";

		String selectFuncionario = querryFuncionario(funcionario);
		if (flgFuncionario)
			querry += " WHERE " + selectFuncionario + " AND F.fun_usu_id = U.usu_id";

		else if (!flgFuncionario)
			querry += " WHERE  F.fun_usu_id = U.usu_id";

		System.out.println(querry);
		return querry;

	}


	private String querryFuncionario(Funcionario funcionario) {
		boolean flgWhere = false;
		String selectFuncionario = "";
		listQuerryFuncionario = new ArrayList<Integer>();

		// id
		if (funcionario.getId() > 0) {
			mapFiltroQuerry.put(0, "fun_id = " + funcionario.getId());
			listQuerryFuncionario.add(0);
			flgFuncionario = true;
			flgWhere = true;
		}
		// nome
		if (funcionario.getNome() != null) {
			if (!funcionario.getNome().trim().equals("")) {
				mapFiltroQuerry.put(1, "fun_nome LIKE '%" + funcionario.getNome() + "%'");
				listQuerryFuncionario.add(1);
				flgWhere = true;
				flgFuncionario = true;
			}
		}
		// cpf
		if (funcionario.getCpf() != null) {
			if (!funcionario.getCpf().trim().equals("")) {
				mapFiltroQuerry.put(2, "fun_cpf LIKE '%" + funcionario.getCpf() + "%'");
				listQuerryFuncionario.add(2);
				flgWhere = true;
				flgFuncionario = true;
			}
		}

		// email
		if (funcionario.getEmail() != null) {
			if (!funcionario.getCpf().trim().equals("")) {
				mapFiltroQuerry.put(3, "fun_email LIKE '%" + funcionario.getEmail() + "%'");
				listQuerryFuncionario.add(3);
				flgWhere = true;
				flgFuncionario = true;
			}
		}

		// matricula
		if (funcionario.getMatricula() != null) {
			if (!funcionario.getMatricula().trim().equals("")) {
				mapFiltroQuerry.put(4, "fun_matricula LIKE '%" + funcionario.getMatricula() + "%'");
				listQuerryFuncionario.add(4);
				flgWhere = true;
				flgFuncionario = true;
			}
		}

		// teve filtro?
		if (flgWhere) {
			for (Integer i : listQuerryFuncionario) {
				if (i != listQuerryFuncionario.get(0))
					selectFuncionario += " AND ";

				selectFuncionario += mapFiltroQuerry.get(i);
			}
		}

		return selectFuncionario;

	}

	private String querryUsuario(Usuario usuario) {
		boolean flgWhere = false;
		String selectUsuario = ", (SELECT * FROM tb_usuario";

		listQuerryUsuario = new ArrayList<Integer>();

		// USUARIO
		if (usuario != null) {
			if (usuario.getLogin() != null) {
				if (!usuario.getLogin().trim().equals("")) {
					mapFiltroQuerry.put(22, "usuario_usu LIKE '%" + usuario.getLogin() + "%'");
					listQuerryUsuario.add(22);
					flgWhere = true;
					flgUsuario = true;
				}
			}

			// ativo
			/*
			 * if(usuario.isAtivoInativo()) { mapFiltroQuerry.put(23,
			 * "ativo_inativo_usu = true"); listQuerryUsuario.add(23); flgWhere = true;
			 * flgUsuario = true; } else { mapFiltroQuerry.put(23,
			 * "ativo_inativo_usu = false"); listQuerryUsuario.add(23); flgWhere = true;
			 * flgUsuario = true;
			 * 
			 * }
			 */

			if (flgWhere) {
				selectUsuario += " WHERE ";
				for (Integer i : listQuerryUsuario) {
					if (i != listQuerryUsuario.get(0))
						selectUsuario += " AND ";
					selectUsuario += mapFiltroQuerry.get(i);
				}

			}

			selectUsuario += ") AS U";
			return selectUsuario;
		}

		return null;

	}

}
