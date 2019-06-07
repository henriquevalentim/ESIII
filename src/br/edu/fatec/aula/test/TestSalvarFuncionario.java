package br.edu.fatec.aula.test;

import br.edu.fatec.aula.controle.Fachada;
import br.edu.fatec.aula.controle.IFachada;
import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.PerfilAtendimento;
import br.edu.fatec.aula.dominio.Regional;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.dominio.Usuario;

public class TestSalvarFuncionario {

	public static void main(String[] args) {
		//colocar um dos 5 perfis dos requisitos funcionais --Atendente, Triagem Inicial, 
		//Triagem de Grupo, Administrador e Administrador de Sistema
		PerfilAtendimento perfil = new PerfilAtendimento("Atendente");
		
		//senha deve ser maior ou igual a 8
		Usuario usuario = new Usuario("admin", "1235465123", perfil);
		
		Regional regional = new Regional("Sul");

		Cargo cargo = new Cargo("Administrador");
		
		Setor setor = new Setor("Administração");
		//o cpf deve ser valido
		Funcionario f1 = new Funcionario("Silvana","17463944808","ttttt16@gmail.com",
				"123512151",cargo,regional,usuario,setor);

		//FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
		//funcionarioDAO.salvar(f1);

		IFachada fachada = new Fachada();
		
		System.out.println(fachada.salvar(f1));
		
	}
}
