package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.Regional;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.util.Conexao;

public class FuncionarioDAO implements IDAO {

	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Funcionario funcionario = (Funcionario) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			connection.setAutoCommit(false);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_funcionario(fun_nome, fun_cpf, ");
			sql.append(
					"fun_email,fun_matricula,fun_senha,fun_reg_id,fun_car_id,fun_usu_id,fun_set_id,fun_dtcontratacao) VALUES (?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setString(3, funcionario.getEmail());
			pst.setString(4, funcionario.getMatricula());
			pst.setString(5, funcionario.getSenha());
			pst.setInt(6, funcionario.getRegional().getId());
			pst.setInt(7, funcionario.getCargo().getId());
			pst.setInt(8, funcionario.getUsuario().getId());
			pst.setInt(9, funcionario.getSetor().getId());
			pst.setString(10, new Date().toString());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int idFuncionario = 0;
			if (rs.next())
				idFuncionario = rs.getInt(1);
			funcionario.setId(idFuncionario);

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Funcionario funcionario = (Funcionario) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_funcionario SET fun_nome = ?, fun_cpf = ?,");
			sql.append("fun_email = ?,fun_reg_id = ?,fun_car_id=?,");
			sql.append("fun_set_id=? where fun_matricula = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setString(3, funcionario.getEmail());
			pst.setInt(4, funcionario.getRegional().getId());
			pst.setInt(5, funcionario.getCargo().getId());
			pst.setInt(6, funcionario.getSetor().getId());
			pst.setString(7, funcionario.getMatricula());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Funcionario funcionario = (Funcionario) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_funcionario SET fun_status = false WHERE fun_matricula = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setString(1, funcionario.getMatricula());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
	
		Funcionario filtroFuncionario = (Funcionario) entidade;

		if (filtroFuncionario.getMatricula() == null) {
			filtroFuncionario.setMatricula("");
		}
		if (filtroFuncionario.getNome() == null) {
			filtroFuncionario.setNome("");
		}
		if (filtroFuncionario.getCpf() == null) {
			filtroFuncionario.setCpf("");
		}
		if (filtroFuncionario.getEmail() == null) {
			filtroFuncionario.setEmail("");
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select emp.fun_id,emp.fun_nome,emp.fun_cpf,emp.fun_email,emp.fun_matricula,emp.fun_reg_id,");
		sql.append("emp.fun_car_id,emp.fun_set_id from tb_funcionario emp where fun_id > 0");

		
		if (filtroFuncionario.getId() != 0 && filtroFuncionario.getMatricula().equals("")) {
			sql.append(" and emp.fun_id=?");
		}
		if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getMatricula().equals("")) {
			sql.append(" and emp.fun_matricula like ?");
		}
		if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getNome().equals("")) {
			sql.append(" and emp.fun_nome like ?");
		}
		if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getCpf().equals("")) {
			sql.append(" and emp.fun_cpf like ?");
		}
		if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getEmail().equals("")) {
			sql.append(" and emp.fun_email like ?");
		}

		List<EntidadeDominio> funcionarios = new ArrayList<>();
		try {
			connection = Conexao.getConnectionPostgres();

			pst = connection.prepareStatement(sql.toString());
			int i = 1;
			if (filtroFuncionario.getId() != 0
					&& filtroFuncionario.getMatricula().equals("")) {
				pst.setInt(1, filtroFuncionario.getId());
			} 
			if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getMatricula().equals("")) {
				pst.setString(i++, "%" + filtroFuncionario.getMatricula() + "%");
			}
			if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getNome().equals("")) {
				pst.setString(i++, "%" + filtroFuncionario.getNome() + "%");
			}
			if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getCpf().equals("")) {
				pst.setString(i++, "%" + filtroFuncionario.getCpf() + "%");
			}
			if (filtroFuncionario.getId() == 0 && !filtroFuncionario.getEmail().equals("")) {
				pst.setString(i++, "%" + filtroFuncionario.getEmail() + "%");
			}
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				Cargo cargo = new Cargo();
				Setor setor = new Setor();
				Regional regional = new Regional();
				funcionario.setId(rs.getInt("fun_id"));
				funcionario.setNome(rs.getString("fun_nome"));
				funcionario.setCpf(rs.getString("fun_cpf"));
				funcionario.setEmail(rs.getString("fun_email"));
				funcionario.setMatricula(rs.getString("fun_matricula"));

				cargo.setId(rs.getInt("fun_car_id"));
				setor.setId(rs.getInt("fun_set_id"));
				regional.setId(rs.getInt("fun_reg_id"));
				
				//cargo.setDescricao(rs.getString("car.car_descricao"));
				//setor.setNome(rs.getString("setor.set_nome"));
				//regional.setNome(rs.getString("reg.reg_nome"));

				funcionario.setCargo(cargo);
				funcionario.setSetor(setor);
				funcionario.setRegional(regional);

				funcionarios.add(funcionario);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return funcionarios;
	}
}
