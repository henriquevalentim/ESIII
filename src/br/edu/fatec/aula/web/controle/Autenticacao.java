package br.edu.fatec.aula.web.controle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import br.edu.fatec.aula.dao.UsuarioDAO;
import br.edu.fatec.aula.dominio.Usuario;

@WebServlet("/Autenticacao")
public class Autenticacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Autenticacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("txtLogin");
		String senha = request.getParameter("txtSenha");
		
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioAutenticado = null;
		usuarioAutenticado = (Usuario)usuarioDAO.autenticar(usuario);
		
		if(usuarioAutenticado != null) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioAutenticado", usuarioAutenticado);
			
			request.getRequestDispatcher("FormFuncionario.jsp").forward(request,response);
		}else {
			response.sendRedirect("login.jsp");
		}
	}

}
