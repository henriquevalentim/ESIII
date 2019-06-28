package br.edu.fatec.aula.web.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.PerfilAtendimento;
import br.edu.fatec.aula.dominio.Regional;
import br.edu.fatec.aula.dominio.Resultado;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.dominio.Usuario;

public class FuncionarioVH implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {

		HttpSession session = null;
		Funcionario funcionario = null;
		String operacao = request.getParameter("operacao");

		if (operacao.equals("SALVAR")) {
			funcionario = criarFuncionario(request);

		} else if (operacao.equals("ALTERAR")) {
			funcionario = criarFuncionario(request);

		} else if (operacao.equals("EXCLUIR")) {
			funcionario = criarFuncionario(request);

		} else if (operacao.equals("CONSULTAR")) {
			funcionario = criarFuncionario(request);

		} else if (operacao.equals("PERFIL")) {
			int id_funcionario = Integer.parseInt(request.getParameter("id"));
			funcionario = new Funcionario();
			funcionario.setId(id_funcionario);
		}

		return funcionario;

	}

	private Funcionario criarFuncionario(HttpServletRequest request) {

		Funcionario funcionario = new Funcionario();
		Cargo cargo = new Cargo();
		Setor setor = new Setor();
		Regional regional = new Regional();
		PerfilAtendimento perfil = new PerfilAtendimento();

		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("txtEmail"));
		usuario.setSenha(request.getParameter("txtSenha"));

		String idFuncionario = request.getParameter("txtIdFuncionario");
		String idPerfil = request.getParameter("txtPerfil");
		String idCargo = request.getParameter("txtCargo");
		String idSetor = request.getParameter("txtSetor");
		String idRegional = request.getParameter("txtRegional");
		String idCadastradoPor = request.getParameter("txtCadastradoPor");

		if (idPerfil != null && !idPerfil.trim().equals("")) {
			perfil.setId(Integer.parseInt(idPerfil));
		}

		usuario.setPerfilAtendimento(perfil);
		funcionario.setNome(request.getParameter("txtNome"));
		funcionario.setCpf(request.getParameter("txtCPF"));
		funcionario.setEmail(request.getParameter("txtEmail"));
		funcionario.setMatricula(request.getParameter("txtMatricula"));
		funcionario.setSenha(request.getParameter("txtSenha"));

		if (idFuncionario != null && !idFuncionario.trim().equals("")) {
			funcionario.setId(Integer.parseInt(idFuncionario));
		}

		if (idCadastradoPor != null && !idCadastradoPor.trim().equals("")) {
			funcionario.setCadastradoPor(Integer.parseInt(idCadastradoPor));
		}

		if (idCargo != null && !idCargo.trim().equals("")) {
			cargo.setId(Integer.parseInt(idCargo));
		}

		if (idSetor != null && !idSetor.trim().equals("")) {
			setor.setId(Integer.parseInt(idSetor));
		}

		if (idRegional != null && !idRegional.trim().equals("")) {
			regional.setId(Integer.parseInt(idRegional));
		}

		funcionario.setUsuario(usuario);
		funcionario.setCargo(cargo);
		funcionario.setSetor(setor);
		funcionario.setRegional(regional);

		return funcionario;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");

		if (resultado.getMsg() != null && !resultado.getMsg().trim().equals("")) {
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("SALVAR")) {
			// Funcionario funcionario = (Funcionario) resultado.getEntidades().get(0);
			resultado.setMsg("Cadastro realizado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			resultado.setMsg("Consulta realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("ALTERAR")) {
			resultado.setMsg("Alteração realizada com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			resultado.setMsg("Funcionario inativado com sucesso.");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("PERFIL")) {
			Funcionario funcionario = (Funcionario) resultado.getEntidades().get(0);
			request.getSession().setAttribute("funcionario", funcionario);
			d = request.getRequestDispatcher("FormFuncionario.jsp");
		}

		d.forward(request, response);

	}
}
