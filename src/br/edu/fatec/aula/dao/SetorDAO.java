package br.edu.fatec.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.dominio.Usuario;
import br.edu.fatec.aula.util.Conexao;

public class SetorDAO implements IDAO {

	private Connection connection;
	private boolean ctrlTransaction = true;

	public SetorDAO() {
	}

	public SetorDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

		PreparedStatement pst = null;
		Setor setor = (Setor) entidade;
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO tb_setor(set_nome) VALUES (?) ");

		try {
			if (connection == null) {
				connection = Conexao.getConnectionPostgres();

			} else {
				ctrlTransaction = false;
			}

			connection.setAutoCommit(false);

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, setor.getNome());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int idSetor = 0;
			if (rs.next())
				idSetor = rs.getInt(1);
			setor.setId(idSetor);

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (ctrlTransaction) {
				try {
					pst.close();
					if (ctrlTransaction)
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

		/*
		 * PreparedStatement pst = null; Setor setor = (Setor) entidade;
		 * 
		 * try { connection = Conexao.getConnectionPostgres();
		 * 
		 * StringBuilder sql = new StringBuilder();
		 * sql.append("UPDATE tb_setor SET set_nome = ? where set_id = ?");
		 * 
		 * pst = connection.prepareStatement(sql.toString());
		 * 
		 * pst.setString(1, setor.getNome()); pst.setInt(2, setor.getId());
		 * 
		 * pst.executeUpdate(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	@Override
	public void excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean consultarExistencia(EntidadeDominio entidade) {
		PreparedStatement pst = null;
		ResultSet rs;

		Setor setor = (Setor) entidade;

		try {
			connection = Conexao.getConnectionPostgres();

			pst = connection.prepareStatement("SELECT * FROM tb_setor where set_id=?");
			pst.setInt(1, setor.getId());

			rs = pst.executeQuery();

			while (rs.next()) {
				setor.setNome(rs.getString("set_nome"));
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
		if(setor.getNome() != null) {
			return true;
		}else {
			return false;
		}
	}
}