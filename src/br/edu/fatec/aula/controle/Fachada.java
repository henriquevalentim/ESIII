package br.edu.fatec.aula.controle;

import java.util.List;

import br.edu.fatec.aula.dao.FuncionarioDAO;
import br.edu.fatec.aula.dao.IDAO;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class Fachada implements IFachada {

	public String salvar(EntidadeDominio entidade) {
		Funcionario funcionario = (Funcionario) entidade;
		StringBuilder sb = new StringBuilder();

		// validaNull(sb, funcionario.validarDados());
		// validaNull(sb, funcionario.getCargo().validarCargo());
		// validaNull(sb, funcionario.getRegional().validarRegional());
		// validaNull(sb, funcionario.getSetor().validarSetor());
		// validaNull(sb, funcionario.getUsuario().validarUsuario());
		// validaNull(sb,
		// funcionario.getUsuario().getPerfilAtendimento().validarPerfilAtendimento());
		// validaNull(sb, funcionario.validarCPF());

		// validaNull(sb, validarExistencia());

		if (sb.length() == 0) {
			// cliente.complementarDtCadastro();
			IDAO dao = new FuncionarioDAO();
			dao.salvar(funcionario);
			return "Funcionario SALVO COM SUCESSO!\n";
		}

		return sb.toString();
	}

	public String alterar(EntidadeDominio entidade) {

		Funcionario funcionario = (Funcionario) entidade;
		StringBuilder sb = new StringBuilder();

		if (sb.length() == 0) {
			// cliente.complementarDtCadastro();
			IDAO dao = new FuncionarioDAO();
			dao.alterar(funcionario);
			return "Funcionario ALTERADO COM SUCESSO!\n";
		}

		return sb.toString();
	}

	public String excluir(EntidadeDominio entidade) {
		
		Funcionario funcionario = (Funcionario) entidade;
		StringBuilder sb = new StringBuilder();

		if (sb.length() == 0) {
			// cliente.complementarDtCadastro();
			IDAO dao = new FuncionarioDAO();
			dao.excluir(funcionario);
			return "Funcionario EXCLUIDO COM SUCESSO!\n";
		}

		return sb.toString();
	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	private void validaNull(StringBuilder sb, String msg) {
		if (msg != null) {
			sb.append(msg);
		}
	}

}
