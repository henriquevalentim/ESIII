package br.edu.fatec.aula.web.controle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.aula.command.AlterarCommand;
import br.edu.fatec.aula.command.ConsultarCommand;
import br.edu.fatec.aula.command.ExcluirCommand;
import br.edu.fatec.aula.command.ICommand;
import br.edu.fatec.aula.command.SalvarCommand;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Resultado;
import br.edu.fatec.aula.web.vh.FuncionarioVH;
import br.edu.fatec.aula.web.vh.IViewHelper;

@WebServlet(urlPatterns = {"/ControleFuncionario"})
public class ControleFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;

	public ControleFuncionario() {
		commands = new HashMap<String, ICommand>();
		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("PERFIL", new ConsultarCommand());
		
		vhs = new HashMap<String, IViewHelper>();
		vhs.put("/TrabESIII/ControleFuncionario", new FuncionarioVH());
	}

	protected void doProcessServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();		
		String operacao = request.getParameter("operacao");
		
		IViewHelper vh = vhs.get(uri);
		EntidadeDominio entidade = vh.getEntidade(request);

		ICommand command = null;
		Resultado resultado = null;

		command = commands.get(operacao);
		resultado = command.executar(entidade);
		
		vh.setView(resultado, request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcessServlet(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProcessServlet(request, response);

	}
}
