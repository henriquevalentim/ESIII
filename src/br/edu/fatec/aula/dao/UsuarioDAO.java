package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.PerfilAtendimento;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.dominio.Usuario;
import br.edu.fatec.aula.web.util.Conexao;

public class UsuarioDAO implements IDAO {

	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public UsuarioDAO(){}
	
	public UsuarioDAO(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Usuario usuario = (Usuario)entidade;
		
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
			}else{
				ctrlTransaction = false;
			}
			connection.setAutoCommit(false);
			
			//PerfilAtendimentoDAO perfilUsuarioDAO = new PerfilAtendimentoDAO(connection);
			//usuario.setPerfilAtendimento((PerfilAtendimento) perfilUsuarioDAO.consultarExistencia(usuario.getPerfilAtendimento()));
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO tb_usuario(usu_login,usu_senha,usu_per_id) VALUES (?,?,?) ");
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
			pst.setInt(3, usuario.getPerfilAtendimento().getId());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idUsuario=0;
			if(rs.next())
				idUsuario = rs.getInt(1);
			usuario.setId(idUsuario);
			
			connection.commit();					
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();	
		}finally{
			if(ctrlTransaction){
				try {
					pst.close();
					if(ctrlTransaction)
						connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

	@Override
	public void alterar(EntidadeDominio entidade) {
		
		/*PreparedStatement pst = null;
		Cargo cargo = (Cargo) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_cargo SET car_descricao = ? where car_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, cargo.getDescricao());
			pst.setInt(2, cargo.getId());
			
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		
		/*PreparedStatement pst = null;
		Cargo cargo = (Cargo) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM tb_cargo WHERE car_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, cargo.getId());
		
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}
	
	public boolean consultarExistencia(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		Usuario usuario = (Usuario)entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			pst = connection.prepareStatement("SELECT * FROM tb_usuario where usu_id=?");
			pst.setInt(1, usuario.getId());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				usuario.setLogin(rs.getString("usu_login"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					pst.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		if(usuario.getLogin() != null) {
			return true;
		}else {
			return false;
		}
	}
	
	public EntidadeDominio autenticar(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		Usuario usuario = (Usuario)entidade;
		Usuario usuarioAutenticado = null;
		try {
			connection = Conexao.getConnectionPostgres();

			
			StringBuilder sql = new StringBuilder();
			sql.append("select usuario.usu_id,usuario.usu_login,usuario.usu_senha,");
			sql.append("usuario.usu_per_id, perfil.per_id,perfil.per_nome ");
			sql.append("from tb_usuario as usuario, tb_perfilatendimento as perfil ");
			sql.append("where usuario.usu_per_id = perfil.per_id and usu_login=? and usu_senha=?;");
			
			pst = connection.prepareStatement(sql.toString());
			pst.setString(1, usuario.getLogin());
			pst.setString(2, usuario.getSenha());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				usuarioAutenticado = new Usuario();
				usuarioAutenticado.setId(Integer.parseInt(rs.getString("usu_id")));
				usuarioAutenticado.setLogin(rs.getString("usu_login"));
				usuarioAutenticado.setSenha(rs.getString("usu_senha"));
				PerfilAtendimento perfilAtendimento = new PerfilAtendimento();
				perfilAtendimento.setId(Integer.parseInt(rs.getString("per_id")));
				perfilAtendimento.setTipoPerfil(rs.getString("per_nome"));
				usuarioAutenticado.setPerfilAtendimento(perfilAtendimento);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
				try {
					pst.close();
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return usuarioAutenticado;
	}

}
