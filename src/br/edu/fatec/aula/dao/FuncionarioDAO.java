package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.PerfilAtendimento;
import br.edu.fatec.aula.dominio.Usuario;
import br.edu.fatec.aula.web.util.Conexao;

public class FuncionarioDAO extends AbstractDAO {

	public FuncionarioDAO() {
		super("tb_funcionario", "fun_id");
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
			sql.append("fun_email,fun_matricula,fun_senha,fun_reg_id,fun_car_id,fun_usu_id,fun_set_id,fun_dtcontratacao) VALUES (?,?,?,?,?,?,?,?,?,?)");

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

		abrirConexao();
		
		Funcionario funcionario =  (Funcionario) entidade;
		PreparedStatement ps = null;
		
		List<EntidadeDominio> listFuncionarios = null;
		
		Filtro filtro = new Filtro();
		
		
		Map<Integer, Funcionario> mapFuncionario;
		Map<Integer, Usuario> mapUsuario;
		List<Funcionario> listFuncionario;
		
		String querry = filtro.gerarQuerry(funcionario);
		
		try {
			listFuncionarios = new ArrayList<EntidadeDominio>();
			
			ps = conexao.prepareStatement(querry);
			ResultSet rs = ps.executeQuery();
			
			mapFuncionario = new HashMap<Integer, Funcionario>();
			mapUsuario = new HashMap<Integer, Usuario>();
			listFuncionario = new ArrayList<Funcionario>();
			
			while(rs.next()) {
				
				// Usuario desse linha
				PerfilAtendimento perfilAten = new PerfilAtendimento();
				perfilAten.setId(rs.getInt("usu_per_id"));
				Usuario usuario = new Usuario();
				usuario.setLogin(rs.getString("usu_login"));
				usuario.setSenha(rs.getString("usu_senha"));
				usuario.setPerfilAtendimento(perfilAten);
				
				// Funcionario dessa linha
			
				Funcionario func = new Funcionario();
				func.setNome(rs.getString("fun_nome"));
				func.setCpf(rs.getString("fun_cpf"));
				func.setEmail(rs.getString("fun_email"));
				func.setMatricula(rs.getString("fun_matricula"));
				
				func.setId(rs.getInt("id_cli"));
				//func.setDtCadastro(rs.getString("dt_cadastro_cli"));
				
				
				// jogando funcionario no map
				if (mapFuncionario.get(func.getId()) == null) {
					mapFuncionario.put(func.getId(), func);
					listFuncionario.add(func);
				}
				
				// jogando usuario no map
				if (mapUsuario.get(func.getId()) == null)
					mapUsuario.put(func.getId(), usuario);
				
				
			}
			
			
			for (Funcionario fun : listFuncionario) {
				// coloquei funcionario
				fun.setUsuario(mapUsuario.get(fun.getId()));
				
				listFuncionarios.add(fun);
				
			}
			
			
			return listFuncionarios;
			
			
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conexao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
}
