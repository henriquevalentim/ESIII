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

			/*
			 * CargoDAO cargoDAO = new CargoDAO(connection);
			 * funcionario.setCargo((Cargo)
			 * cargoDAO.consultarExistencia(funcionario.getCargo()));
			 * 
			 * RegionalDAO regionalDAO = new RegionalDAO(connection);
			 * funcionario.setRegional((Regional)
			 * regionalDAO.consultarExistencia(funcionario.getRegional()));
			 * 
			 * UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			 * funcionario.setUsuario((Usuario)
			 * usuarioDAO.consultarExistencia(funcionario.getUsuario()));
			 * 
			 * SetorDAO setorDAO = new SetorDAO(connection);
			 * funcionario.setSetor((Setor)
			 * setorDAO.consultarExistencia(funcionario.getSetor()));
			 */

			/*
			 * PerfilAtendimento perfilAtendimento = new PerfilAtendimento();
			 * Usuario usuario = new Usuario("admin", "admin",
			 * perfilAtendimento); usuario.setId(1);
			 * funcionario.setUsuario(usuario);
			 */

			// UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			// usuarioDAO.salvar(funcionario.getUsuario());

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_funcionario(fun_nome, fun_cpf, ");
			sql.append("fun_email,fun_matricula,fun_senha,fun_reg_id,fun_car_id,fun_usu_id,fun_set_id,fun_dtcontratacao) VALUES (?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(),
					Statement.RETURN_GENERATED_KEYS);

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
		ResultSet rs;

		List<EntidadeDominio> funcionarios = new ArrayList<>();

		try {
			connection = Conexao.getConnectionPostgres();

			pst = connection.prepareStatement("SELECT * FROM tb_funcionario where fun_status = true");
			rs = pst.executeQuery();

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
