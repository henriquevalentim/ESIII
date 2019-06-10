package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.web.util.Conexao;

public abstract class AbstractDAO implements IDAO {

	protected Connection conexao;
	protected String idTable;
	protected String nomeTabela;
	protected boolean ctrlTransacao = true;

	
	public AbstractDAO(Connection conexao, String nomeTabela, String idTable) {
		super();
		this.conexao = conexao;
		this.idTable = idTable;
		this.nomeTabela = nomeTabela;
	}

	public AbstractDAO(String nomeTabela, String idTable) {
		super();
		this.idTable = idTable;
		this.nomeTabela = nomeTabela;
	}
	
	public void abrirConexao() { 
		
		try {
			if (conexao == null || conexao.isClosed()) {
				conexao = Conexao.getConnectionPostgres();
			}
		} catch (SQLException|ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		if(conexao == null)
			abrirConexao();
		else
			ctrlTransacao = false;
		
		PreparedStatement ps = null;
		StringBuilder sb = new StringBuilder();
		
		if (!entidade.getClass().getName().equals(Funcionario.class.getName())) {
			sb.append("DELETE FROM " + nomeTabela + " WHERE " + idTable + " = ?");
			
			try {
				conexao.setAutoCommit(false);
				ps = conexao.prepareStatement(sb.toString());
				ps.setInt(1, entidade.getId());
				
				ps.executeUpdate();
				conexao.commit();
				
			} catch (SQLException e) {
				try {
					conexao.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			}finally {
				if (ctrlTransacao) {
					try {
						ps.close();
						if(ctrlTransacao)
							conexao.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		
		}
		else if (entidade.getClass().getName().equals(Funcionario.class.getName())) {
			sb.append("UPDATE tb_usuario SET ativo_inativo_usu = false WHERE id_cliente = ?");
			try {
				conexao.setAutoCommit(false);
				ps = conexao.prepareStatement(sb.toString());
				ps.setInt(1, entidade.getId());
				
				ps.executeUpdate();
				
				conexao.commit();
			} catch (SQLException e) {
				try {
					conexao.rollback();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				
			}finally {
				if (ctrlTransacao) {
					try {
						ps.close();
						if(ctrlTransacao)
							conexao.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
				
			}
			
			
		}

	}
	
	
}
