package br.edu.fatec.aula.web.fachada;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.aula.dao.FuncionarioDAO;
import br.edu.fatec.aula.dao.IDAO;
import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;
import br.edu.fatec.aula.dominio.Resultado;
import br.edu.fatec.aula.negocio.CompletarDtCadastro;
import br.edu.fatec.aula.negocio.IStrategy;
import br.edu.fatec.aula.negocio.ValidarCpf;
import br.edu.fatec.aula.negocio.ValidarDadosFuncionario;
import br.edu.fatec.aula.negocio.ValidarExistencia;

public class Fachada implements IFachada {

	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rns;
	private StringBuilder sb = new StringBuilder();
	private Resultado resultado;
	
	
	String nmClasse = null;
	
	List<IStrategy> rng = null;

	public Fachada() {
		super();
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, List<IStrategy>>();

		// definindo dao para funcionario
		daos.put(Funcionario.class.getName(), new FuncionarioDAO());

		IStrategy vFuncionario = new ValidarDadosFuncionario();
		IStrategy vCpf = new ValidarCpf();
		IStrategy vExistencia = new ValidarExistencia();
		IStrategy cDtCad = new CompletarDtCadastro();

		List<IStrategy> rnsFuncionario = new ArrayList<IStrategy>();
		rnsFuncionario.add(vFuncionario);
		rnsFuncionario.add(vCpf);
		//rnsFuncionario.add(vExistencia);
		rnsFuncionario.add(cDtCad);

		rns.put(Funcionario.class.getName(), rnsFuncionario);

	}

	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		sb.setLength(0);
		nmClasse = entidade.getClass().getName();
		
		List<IStrategy> rnsEntidade = rns.get(nmClasse);

		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			IDAO dao = daos.get(nmClasse);
			dao.salvar(entidade);
			resultado.addEntidades(entidade);
		} else {
			resultado.addEntidades(entidade);
			resultado.setMsg(sb.toString());
		}

		return resultado;

	}

	private void executarRegras(EntidadeDominio entidade, List<IStrategy> rnsEntidade) {
		for (IStrategy rn : rnsEntidade) {
			String msg = rn.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}
	}

	public Resultado alterar(EntidadeDominio entidade) {
		sb.setLength(0);
		String nmClasse = entidade.getClass().getName();

		List<IStrategy> rnsEntidade = rns.get(nmClasse);

		executarRegras(entidade, rnsEntidade);

		if (sb.length() == 0) {
			IDAO dao = daos.get(nmClasse);
			dao.alterar(entidade);
			resultado.addEntidades(entidade);
		} else {
			resultado.setMsg(sb.toString());
			return resultado;
		}

		return null;
	}

	public Resultado excluir(EntidadeDominio entidade) {

		Funcionario funcionario = (Funcionario) entidade;
		StringBuilder sb = new StringBuilder();

		if (sb.length() == 0) {
			// cliente.complementarDtCadastro();
			IDAO dao = new FuncionarioDAO();
			dao.excluir(funcionario);
			resultado.addEntidades(entidade);
		} else {
			resultado.setMsg(sb.toString());
			return resultado;
		}

		return null;
	}

	public Resultado consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
