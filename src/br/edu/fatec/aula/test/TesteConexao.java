package br.edu.fatec.aula.test;

import java.sql.Connection;
import java.sql.SQLException;

import br.edu.fatec.aula.web.util.Conexao;

public class TesteConexao {

	public static void main(String[] args) {
		try {
			Connection cx = Conexao.getConnectionPostgres();
			
			if(cx == null){
				System.out.println("CONEXÃO NÃO ESTABELECIDA");
			}else{
				System.out.println("CONEXÃO ESTABELECIDA");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
