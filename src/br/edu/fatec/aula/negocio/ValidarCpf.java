package br.edu.fatec.aula.negocio;

import java.util.InputMismatchException;

import br.edu.fatec.aula.dominio.EntidadeDominio;
import br.edu.fatec.aula.dominio.Funcionario;

public class ValidarCpf implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		
		Funcionario funcionario = (Funcionario)entidade;

		if (funcionario.getCpf().equals("00000000000") ||
				funcionario.getCpf().equals("11111111111") ||
				funcionario.getCpf().equals("22222222222") || 
				funcionario.getCpf().equals("33333333333") ||
				funcionario.getCpf().equals("44444444444") || 
				funcionario.getCpf().equals("55555555555") ||
				funcionario.getCpf().equals("66666666666") || 
				funcionario.getCpf().equals("77777777777") ||
				funcionario.getCpf().equals("88888888888") || 
				funcionario.getCpf().equals("99999999999") ||
	            (funcionario.getCpf().length() != 11))
	            return("CPF INVALIDO<br>");
	          
	        char dig10, dig11;
	        int sm, i, r, num, peso;
	          
	        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
	        try {
	        // Calculo do 1o. Digito Verificador
	            sm = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {              
	        // converte o i-esimo caractere do CPF em um numero:
	        // por exemplo, transforma o caractere '0' no inteiro 0         
	        // (48 eh a posicao de '0' na tabela ASCII)         
	            num = (int)(funcionario.getCpf().charAt(i) - 48); 
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }
	          
	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
	          
	        // Calculo do 2o. Digito Verificador
	            sm = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
	            num = (int)(funcionario.getCpf().charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }
	          
	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                 dig11 = '0';
	            else dig11 = (char)(r + 48);
	          
	        // Verifica se os digitos calculados conferem com os digitos informados.
	            if ((dig10 == funcionario.getCpf().charAt(9)) && (dig11 == funcionario.getCpf().charAt(10)))
	                 return null;
	            else return("CPF INVALIDO<br>");
	                } catch (InputMismatchException erro) {
	                return("CPF INVALIDO<br>");
	            }
		
	}
	
	

}
