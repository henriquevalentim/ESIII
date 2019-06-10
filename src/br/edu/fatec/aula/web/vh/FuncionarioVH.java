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

			// Criei o cliente com os campos que está no alterar
			funcionario = criarFuncionario(request);

			// colocar o id do clienteSession no cliente
			funcionario.setId(Integer.parseInt(request.getParameter("id")));

		} else if (operacao.equals("EXCLUIR")) {
			session = request.getSession();
			funcionario = (Funcionario) session.getAttribute("cliente");

		} else if (operacao.equals("CONSULTAR")) {
			funcionario = criarFuncionario(request);

		}

		return funcionario;

	}

	private Funcionario criarFuncionario(HttpServletRequest request) {
		String operacao = request.getParameter("operacao");

		Funcionario funcionario = new Funcionario();
		Cargo cargo = new Cargo();
		Setor setor = new Setor();
		Regional regional = new Regional();

		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("txtEmail"));
		usuario.setSenha(request.getParameter("txtSenha"));
		String idCargo = request.getParameter("txtCargo");
		String idSetor = request.getParameter("txtSetor");
		String idRegional = request.getParameter("txtRegional");
		funcionario.setNome(request.getParameter("txtNome"));
		funcionario.setCpf(request.getParameter("txtCPF"));
		funcionario.setEmail(request.getParameter("txtEmail"));
		funcionario.setMatricula(request.getParameter("txtMatricula"));
		funcionario.setSenha(request.getParameter("txtSenha"));

		if(idCargo != null && !idCargo.trim().equals("")) {
			cargo.setId(Integer.parseInt(idCargo));
		}
		
		if(idSetor != null && !idSetor.trim().equals("")) {
			setor.setId(Integer.parseInt(idSetor));
		}
		
		if(idRegional != null && !idRegional.trim().equals("")) {
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
			Funcionario funcionario = (Funcionario) resultado.getEntidades().get(0);
			System.out.println("TO NA SALVAR VIEW");
			request.getSession().setAttribute("id_cliente", funcionario.getId());
			request.getSession().setAttribute("funcionario", funcionario);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("CONSULTAR")) {
			System.out.println("TO NO PESQUISAR VIEW");
			request.getSession().setAttribute("resultado", resultado);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("ALTERAR")) {
			System.out.println("TO NO ALTERAR SET VIEW");
			Funcionario funcionario = (Funcionario) resultado.getEntidades().get(0);
			request.getSession().setAttribute("funcionario", funcionario);
			d = request.getRequestDispatcher("FormFuncionario.jsp");

		} else if (operacao.equals("EXCLUIR")) {
			System.out.println("TO NA EXCLUIR VIEW");
			d = request.getRequestDispatcher("FormFuncionario.html");

		}

		d.forward(request, response);

	}

}
