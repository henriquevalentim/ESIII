package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.Regional;
import br.edu.fatec.aula.util.Conexao;

public class RegionalDAO implements IDAO {

	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public RegionalDAO(){}
	
	public RegionalDAO(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Regional regional = (Regional)entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_regional(reg_nome) VALUES (?) ");
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
				 
			}else{
				ctrlTransaction = false;
			}
		
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, regional.getNome());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idRegional=0;
			if(rs.next())
				idRegional = rs.getInt(1);
			regional.setId(idRegional);
			
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
		Regional regional = (Regional) entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_regional SET reg_nome = ? where reg_id = ?");
			
			pst = connection.prepareStatement(sql.toString());
			
			pst.setString(1, regional.getNome());
			pst.setInt(2, regional.getId());
			
			pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void excluir(EntidadeDominio entidade) {

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		return null;
	}
	
	public boolean consultarExistencia(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		Regional regional = (Regional)entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			pst = connection.prepareStatement("SELECT * FROM tb_regional where reg_id=?");
			pst.setInt(1, regional.getId());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				regional.setNome(rs.getString("reg_nome"));
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
		if(regional.getNome() != null) {
			return true;
		}else {
			return false;
		}
	}

}
