package br.edu.fatec.aula.dao;

import java.util.List;

import br.edu.fatec.aula.dominio.EntidadeDominio;

public interface IDAO {
	public void salvar(EntidadeDominio entidade);
	public void alterar(EntidadeDominio entidade);
	public void excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}
