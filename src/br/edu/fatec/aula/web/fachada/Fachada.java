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
import br.edu.fatec.aula.strategy.CompletarDtCadastro;
import br.edu.fatec.aula.strategy.IStrategy;
import br.edu.fatec.aula.strategy.ValidarCpf;
import br.edu.fatec.aula.strategy.ValidarDadosFuncionario;
import br.edu.fatec.aula.strategy.ValidarSenha;

public class Fachada implements IFachada {

	private Map<String, IDAO> daos;
	private Map<String, List<IStrategy>> rns;
	private StringBuilder sb = new StringBuilder();
	Resultado resultado;

	IDAO dao = null;
	String nmClasse = null;
	List<IStrategy> rng = null;

	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, List<IStrategy>>();

		// definindo dao para funcionario
		daos.put(Funcionario.class.getName(), new FuncionarioDAO());

		IStrategy vFuncionario = new ValidarDadosFuncionario();
		IStrategy vCpf = new ValidarCpf();
		IStrategy cDtCad = new CompletarDtCadastro();
		IStrategy vSenha = new ValidarSenha();

		List<IStrategy> rnsFuncionario = new ArrayList<IStrategy>();
		rnsFuncionario.add(vFuncionario);
		rnsFuncionario.add(vCpf);
		rnsFuncionario.add(cDtCad);
		rnsFuncionario.add(vSenha);

		rns.put(Funcionario.class.getName(), rnsFuncionario);

	}

	public Resultado salvar(EntidadeDominio entidade) {

		resultado = new Resultado();
		nmClasse = entidade.getClass().getName();
		rng = rns.get(nmClasse);
		sb.setLength(0);

		executarRegras(rng, entidade);

		// se tem msg de erro ele n�o salva
		if (sb.length() == 0 || sb.toString().trim().equals("")) {
			try {
				dao = daos.get(nmClasse);
				dao.salvar(entidade);
				resultado.addEntidades(entidade);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("N�o foi poss�vel Salvar...");
			}
		} else {

			resultado.addEntidades(entidade);
			resultado.setMsg(sb.toString());
		}

		return resultado;

	}

	private void executarRegras(List<IStrategy> rngEntidade, EntidadeDominio entidade) {
		String msg = "";
		for (IStrategy strategy : rngEntidade) {
			msg = strategy.processar(entidade);
			if (msg != null) {
				sb.append(msg);
			}
		}
	}

	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		sb.setLength(0);

		nmClasse = entidade.getClass().getName();

		executarRegras(rns.get(nmClasse), entidade);

		if (sb.toString().trim().equals("")) {
			try {
				dao = daos.get(nmClasse);
				dao.alterar(entidade);
				resultado.addEntidades(entidade);

			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg(sb + "\nN�o foi poss�vel alterar...");
			}
		} else {
			resultado.setMsg(sb.toString());
			resultado.addEntidades(entidade);
		}

		return resultado;

	}

	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();

		String nmClasse = entidade.getClass().getName();

		dao = daos.get(nmClasse);

		try {
			dao.excluir(entidade);
			resultado.addEntidades(entidade);

		} catch (Exception e) {
			e.printStackTrace();
			resultado.setMsg("N�o foi poss�vel Excluir...");
		}

		return resultado;
	}

	public Resultado consultar(EntidadeDominio entidade) {
		sb.setLength(0);
		resultado = new Resultado();
		
		nmClasse = entidade.getClass().getName();
		
		dao = daos.get(nmClasse);
		
		try {
			resultado.setEntidades(dao.consultar(entidade));
		}catch(Exception e) {
			e.printStackTrace();
			resultado.setMsg("N�o foi poss�vel realizar a consulta...");
		}
		return resultado;
	}

}
