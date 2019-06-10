package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.PerfilAtendimento;
import br.edu.fatec.aula.dominio.Usuario;
import br.edu.fatec.aula.web.util.Conexao;

public class PerfilAtendimentoDAO implements IDAO {

	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public PerfilAtendimentoDAO(){}
	
	public PerfilAtendimentoDAO(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		PerfilAtendimento perfilUsuario = (PerfilAtendimento)entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_perfilatendimento(per_nome) VALUES (?) ");
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
				 
			}else{
				ctrlTransaction = false;
			}
		
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, perfilUsuario.getTipoPerfil());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idPerfil=0;
			if(rs.next())
				idPerfil = rs.getInt(1);
			perfilUsuario.setId(idPerfil);
			
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
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}
	
	public EntidadeDominio consultarExistencia(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		PerfilAtendimento perfilAtendimento = (PerfilAtendimento)entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			pst = connection.prepareStatement("SELECT * FROM tb_usuario where usu_login=?");
			pst.setString(1, perfilAtendimento.getTipoPerfil());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				perfilAtendimento.setId(rs.getInt("usu_id"));
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
		return perfilAtendimento;
	}

}
