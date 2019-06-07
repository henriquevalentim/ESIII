package br.edu.fatec.aula.dominio;

import java.util.InputMismatchException;

public class Funcionario extends EntidadeDominio {

	private String nome;
	private String cpf;
	private String email;
	private String matricula;
	private String senha;
	

	private Regional regional;
	private Cargo cargo;
	private Setor setor;
	private Usuario usuario;
	private String dtContratacao;

	public Funcionario(String nome, String cpf, String email, String matricula, Cargo cargo,Regional regional,Usuario usuario,Setor setor) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.matricula = matricula;
		this.cargo = cargo;
		this.regional = regional;
		this.usuario = usuario;
		this.setor = setor;

	}

	public Funcionario() {
	}

	public String validarDados() {
		StringBuilder sb = new StringBuilder();

		if (nome == null || nome.trim().equals("")) {
			sb.append("NOME É UM CAMPO OBRIGATÓRIO!\n");
		}

		if (cpf == null || cpf.trim().equals("")) {
			sb.append("CPF É UM CAMPO OBRIGATÓRIO!\n");
		}
		
		if (email == null || email.trim().equals("")) {
			sb.append("EMAIL É UM CAMPO OBRIGATÓRIO!\n");
		}
		
		if (matricula == null || matricula.trim().equals("")) {
			sb.append("MATRICULA É UM CAMPO OBRIGATÓRIO!\n");
		}

		
		/*String msgCargo = cargo.validarCargo();

		if (msgCargo != null) {
			sb.append(msgCargo);
		}*/

		if (sb.length() > 0) {
			return sb.toString();
		} else {
			return null;
		}

	}
	
	public String validarCPF() {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000") ||
        	cpf.equals("11111111111") ||
        	cpf.equals("22222222222") || cpf.equals("33333333333") ||
        	cpf.equals("44444444444") || cpf.equals("55555555555") ||
        	cpf.equals("66666666666") || cpf.equals("77777777777") ||
        	cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return("CPF INVALIDO");
          
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
            num = (int)(cpf.charAt(i) - 48); 
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
            num = (int)(cpf.charAt(i) - 48);
            sm = sm + (num * peso);
            peso = peso - 1;
            }
          
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                 dig11 = '0';
            else dig11 = (char)(r + 48);
          
        // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                 return(null);
            else return("CPF INVALIDO");
                } catch (InputMismatchException erro) {
                return("CPF INVALIDO");
            }
        }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	

}
