package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.util.Conexao;

public class CargoDAO implements IDAO {

	private Connection connection;
	private boolean ctrlTransaction = true;
	
	public CargoDAO(){}
	
	public CargoDAO(Connection connection){
		this.connection = connection;
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		PreparedStatement pst=null;
		Cargo cargo = (Cargo)entidade;
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO tb_cargo(car_descricao) VALUES (?) ");
		
		try {
			if(connection == null){
				connection = Conexao.getConnectionPostgres();
				 
			}else{
				ctrlTransaction = false;
			}
		
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(sql.toString(), 
					Statement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, cargo.getDescricao());
			
			pst.executeUpdate();		
					
			ResultSet rs = pst.getGeneratedKeys();
			int idCargo=0;
			if(rs.next())
				idCargo = rs.getInt(1);
			cargo.setId(idCargo);
			
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

	public EntidadeDominio consultarExistencia(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;
		
		Cargo cargo = (Cargo)entidade;
		
		try {
			connection = Conexao.getConnectionPostgres();
			
			pst = connection.prepareStatement("SELECT * FROM tb_cargo where car_id=?");
			pst.setInt(1, cargo.getId());
			
			rs = pst.executeQuery();
			
			while(rs.next()){
				cargo.setId(rs.getInt("car_id"));
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
		return cargo;
	}
}
