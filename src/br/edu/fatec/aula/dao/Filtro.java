package br.edu.fatec.aula.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.aula.dominio.Funcionario;

public class Filtro {

	private String querry;
	private Map<Integer, String> mapFiltroQuerry;
	private List<Integer> listQuerryFuncionario;

	private boolean flgFuncionario = false;

	public Filtro() {

	}

	public String gerarQuerry(Funcionario funcionario) {

		querry = "SELECT * FROM tb_funcionario as F,tb_cargo as C,tb_regional as R,tb_setor as S";

		mapFiltroQuerry = new HashMap<Integer, String>();

		String selectFuncionario = querryFuncionario(funcionario);
		if (flgFuncionario) {
			querry += " WHERE " + selectFuncionario + " AND F.fun_car_id = C.car_id AND ";
			querry += "F.fun_reg_id = R.reg_id AND F.fun_set_id = S.set_id;";
		} else if (!flgFuncionario) {
			querry += " WHERE F.fun_car_id = C.car_id AND F.fun_reg_id = R.reg_id AND F.fun_set_id = S.set_id;";
		}
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
			if (!funcionario.getEmail().trim().equals("")) {
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

		// cargo
		if (funcionario.getCargo() != null) {
			if (funcionario.getCargo().getId() > 0) {
				mapFiltroQuerry.put(5, "fun_car_id = " + funcionario.getCargo().getId());
				listQuerryFuncionario.add(5);
				flgWhere = true;
				flgFuncionario = true;
			}
		}

		// setor
		if (funcionario.getSetor() != null) {
			if (funcionario.getSetor().getId() > 0) {
				mapFiltroQuerry.put(6, "fun_set_id = " + funcionario.getSetor().getId());
				listQuerryFuncionario.add(6);
				flgWhere = true;
				flgFuncionario = true;
			}
		}
		
		// regional
		if (funcionario.getRegional() != null) {
			if (funcionario.getRegional().getId() > 0) {
				mapFiltroQuerry.put(7, "fun_reg_id = " + funcionario.getRegional().getId());
				listQuerryFuncionario.add(7);
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

//	private String querryCargo(Cargo cargo) {
//		boolean flgWhere = false;
//		flgCargo = true;
//		String selectCargo = ",tb_cargo as C";
//
//		return selectCargo;
//	}

}
