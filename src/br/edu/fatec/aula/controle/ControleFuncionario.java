package br.edu.fatec.aula.controle;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.aula.dao.FuncionarioDAO;
import br.edu.fatec.aula.dao.IDAO;
import br.edu.fatec.aula.dominio.Cargo;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.Regional;
import br.edu.fatec.aula.dominio.Setor;
import br.edu.fatec.aula.dominio.Usuario;

@WebServlet(urlPatterns = { "/ControleFuncionario" })
public class ControleFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		Funcionario funcionario = new Funcionario();
		if(request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			funcionario.setId(id);
		}
			
			IDAO funcionarioDAO = new FuncionarioDAO();
			List<EntidadeDominio> func = funcionarioDAO.consultar(funcionario);
			request.setAttribute("funcionario", func.get(0));
			RequestDispatcher rd = request.getRequestDispatcher("FormFuncionario.jsp");  		
			  
			rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		if (request.getParameter("operacao").equals("SALVAR_FUNCIONARIO")) {
			Funcionario funcionario = new Funcionario();
			Cargo cargo = new Cargo();
			Setor setor = new Setor();
			Regional regional = new Regional();
			
			String idCargo = request.getParameter("txtCargo");
			String idSetor = request.getParameter("txtSetor");
			String idRegional = request.getParameter("txtRegional");
			
			cargo.setId(Integer.parseInt(idCargo));
			setor.setId(Integer.parseInt(idSetor));
			regional.setId(Integer.parseInt(idRegional));
			
			HttpSession session = request.getSession();
			Usuario usu = (Usuario) session.getAttribute("usuarioAutenticado");
			
			funcionario.setNome(request.getParameter("txtNome"));
			funcionario.setCpf(request.getParameter("txtCPF"));
			funcionario.setEmail(request.getParameter("txtEmail"));
			funcionario.setMatricula(request.getParameter("txtMatricula"));
			funcionario.setSenha(request.getParameter("txtSenha"));
			funcionario.setUsuario(usu);
			funcionario.setCargo(cargo);
			funcionario.setSetor(setor);
			funcionario.setRegional(regional);

			IFachada fachada = new Fachada();

			String msg = fachada.salvar(funcionario);
			
			session.setAttribute("msg", msg);
			request.getRequestDispatcher("FormFuncionario.jsp").forward(request, response);
			
		}else if(request.getParameter("operacao").equals("BUSCAR_FUNCIONARIO")) {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setMatricula(request.getParameter("txtMatricula"));
			funcionario.setNome(request.getParameter("txtNome"));
			funcionario.setCpf(request.getParameter("txtCPF"));
			funcionario.setEmail(request.getParameter("txtEmail"));
			funcionario.setId(0);
			
			IFachada fachada = new Fachada();
			
			IDAO dao = new FuncionarioDAO();
			List<EntidadeDominio> funcionarios = dao.consultar(funcionario);
			
			request.setAttribute("resultado", funcionarios);
			
			request.getRequestDispatcher("FormFuncionario.jsp").forward(request, response);

			
		}else if(request.getParameter("operacao").equals("ALTERAR_FUNCIONARIO")) {
			
			Funcionario funcionario = new Funcionario();
			Cargo cargo = new Cargo();
			Setor setor = new Setor();
			Regional regional = new Regional();
			
			String idCargo = request.getParameter("txtCargo");
			String idSetor = request.getParameter("txtSetor");
			String idRegional = request.getParameter("txtRegional");
			
			cargo.setId(Integer.parseInt(idCargo));
			setor.setId(Integer.parseInt(idSetor));
			regional.setId(Integer.parseInt(idRegional));
			
			HttpSession session = request.getSession();
			Usuario usu = (Usuario) session.getAttribute("usuAutenticado");
			
			funcionario.setNome(request.getParameter("txtNome"));
			funcionario.setCpf(request.getParameter("txtCPF"));
			funcionario.setEmail(request.getParameter("txtEmail"));
			funcionario.setMatricula(request.getParameter("txtMatricula"));
			funcionario.setSenha(request.getParameter("txtSenha"));
			funcionario.setUsuario(usu);
			funcionario.setCargo(cargo);
			funcionario.setSetor(setor);
			funcionario.setRegional(regional);
			
			IFachada fachada = new Fachada();

			String msg = fachada.alterar(funcionario);
			
			session.setAttribute("msg", msg);
			request.getRequestDispatcher("FormFuncionario.jsp").forward(request, response);
			
		}else if(request.getParameter("operacao").equals("EXCLUIR_FUNCIONARIO")) {
			
			Funcionario funcionario = new Funcionario();
			
			funcionario.setMatricula(request.getParameter("txtMatricula"));
			
			IFachada fachada = new Fachada();
			HttpSession session = request.getSession();
			String msg = fachada.excluir(funcionario);
			session.setAttribute("msg", msg);
			request.getRequestDispatcher("FormFuncionario.jsp").forward(request, response);
			
		}
	}
}
