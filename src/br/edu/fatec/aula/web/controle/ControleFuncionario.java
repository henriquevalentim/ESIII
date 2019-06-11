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

//,"/AlterarFuncionario","/ExcluirFuncionario","/ConsultarFuncionario" 
@WebServlet(urlPatterns = { "/SalvarFuncionario","/ControleFuncionario","/ConsultarFuncionario" })
public class ControleFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Map<String, ICommand> commands;
	private Map<String, IViewHelper> vhs;

	public ControleFuncionario() {
		super();
		commands = new HashMap<String, ICommand>();

		commands.put("SALVAR", new SalvarCommand());
		commands.put("ALTERAR", new AlterarCommand());
		commands.put("EXCLUIR", new ExcluirCommand());
		commands.put("CONSULTAR", new ConsultarCommand());
		commands.put("PERFIL", new ConsultarCommand());
		
		//PERFIL

		vhs = new HashMap<String, IViewHelper>();

		vhs.put("/TrabESIII/ControleFuncionario", new FuncionarioVH());
		vhs.put("/TrabESIII/SalvarFuncionario", new FuncionarioVH());
		vhs.put("/TrabESIII/AlterarFuncionario", new FuncionarioVH());
		vhs.put("/TrabESIII/ExcluirFuncionario", new FuncionarioVH());
		vhs.put("/TrabESIII/ConsultarFuncionario", new FuncionarioVH());
		vhs.put("/TrabESIII/PerfilFuncionario", new FuncionarioVH());

	}

	protected void doProcessServlet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// obter a operação do botao que foi precionado
		String uri = request.getRequestURI();
		System.out.println("\n\n\n\n\n\n");
		System.out.println(uri);

		// obter a operação clicada pelo botao
		String operacao = request.getParameter("operacao");
		System.out.println(operacao);

		System.out.println("vou instanciar a view");
		IViewHelper vh = vhs.get(uri);

		System.out.println("vou pegar a entidade da view");
		EntidadeDominio entidade = vh.getEntidade(request);

		System.out.println("vou chamar a command");
		ICommand command = null;
		Resultado resultado = null;

		command = commands.get(operacao);
		System.out.println("vou add o resultado");
		resultado = command.executar(entidade);
		
		HttpSession sessao = request.getSession();
		sessao.setAttribute("resultado", resultado);

		System.out.println("setView");
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
