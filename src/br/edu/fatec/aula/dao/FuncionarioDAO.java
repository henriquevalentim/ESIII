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
import br.edu.fatec.aula.dominio.Usuario;
import br.edu.fatec.aula.web.util.Conexao;

public class FuncionarioDAO implements IDAO {

	public FuncionarioDAO() {
		super();
	}

	private Connection connection = null;

	@Override
	public void salvar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Funcionario funcionario = (Funcionario) entidade;

		try {
			connection = Conexao.getConnectionPostgres();
			connection.setAutoCommit(false);

			Usuario usuario = funcionario.getUsuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_funcionario(fun_nome, fun_cpf, ");
			sql.append("fun_email,fun_matricula,fun_senha,fun_cadastradopor_usu_id,fun_reg_id,");
			sql.append("fun_car_id,fun_usu_id,fun_set_id,fun_dtcontratacao) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, funcionario.getNome());
			pst.setString(2, funcionario.getCpf());
			pst.setString(3, funcionario.getEmail());
			pst.setString(4, funcionario.getMatricula());
			pst.setString(5, funcionario.getSenha());
			pst.setInt(6, funcionario.getCadastradoPor());
			pst.setInt(7, funcionario.getRegional().getId());
			pst.setInt(8, funcionario.getCargo().getId());
			pst.setInt(9, funcionario.getUsuario().getId());
			pst.setInt(10, funcionario.getSetor().getId());
			pst.setString(11, new Date().toString());

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
			sql.append("UPDATE tb_funcionario SET fun_status = false WHERE fun_id = ?");

			pst = connection.prepareStatement(sql.toString());

			pst.setInt(1, funcionario.getId());

			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {

		Funcionario funcionario = (Funcionario) entidade;
		PreparedStatement ps = null;

		List<EntidadeDominio> listFuncionarios = null;

		Filtro filtro = new Filtro();

		String querry = filtro.gerarQuerry(funcionario);

		try {
			connection = Conexao.getConnectionPostgres();
			listFuncionarios = new ArrayList<EntidadeDominio>();

			ps = connection.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Setor setor = new Setor();
				Regional regional = new Regional();
				Cargo car = new Cargo();
				Funcionario func = new Funcionario();

				func.setId(rs.getInt("fun_id"));
				func.setNome(rs.getString("fun_nome"));
				func.setCpf(rs.getString("fun_cpf"));
				func.setEmail(rs.getString("fun_email"));
				func.setMatricula(rs.getString("fun_matricula"));

				car.setId(rs.getInt("car_id"));
				car.setDescricao(rs.getString("car_descricao"));
				regional.setId(rs.getInt("reg_id"));
				regional.setNome(rs.getString("reg_nome"));
				setor.setId(rs.getInt("set_id"));
				setor.setNome(rs.getString("set_nome"));

				func.setCargo(car);
				func.setRegional(regional);
				func.setSetor(setor);

				listFuncionarios.add(func);
			}

			return listFuncionarios;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
