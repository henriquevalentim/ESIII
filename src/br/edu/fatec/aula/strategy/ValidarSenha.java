package br.edu.fatec.aula.strategy;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class ValidarSenha implements IStrategy {
	
	private StringBuilder sb = null;

	@Override
	public String processar(EntidadeDominio entidade) {
		sb = new StringBuilder();

		Funcionario funcionario = (Funcionario) entidade;
		
		int numeroMaiuscula = 0;
		int numeroMinuscula = 0;
		int numeroDigitos = 0;
		int numeroSimbolos = 0;
		
		
		for(int i = 0; i < funcionario.getSenha().length();i++) {
			
			if(Character.isUpperCase(funcionario.getSenha().charAt(i))) {
				numeroMaiuscula++;
			}else if(Character.isLowerCase(funcionario.getSenha().charAt(i))) {
				numeroMinuscula++;
			}else if(Character.isDigit(funcionario.getSenha().charAt(i))) {
				numeroDigitos++;
			}else {
				numeroSimbolos++;
			}
		}
		
		if (funcionario.getSenha().length() < 8) {
			sb.append("TAMANHO DE SENHA INVÁLIDO!<br>");
		}

		if (numeroMaiuscula == 0 || numeroMinuscula == 0 || numeroDigitos == 0 || numeroSimbolos == 0 ) {
			sb.append("SENHA NO FORMATO INCORRETO!<br>");
		}

		return sb.toString();

	}

}
