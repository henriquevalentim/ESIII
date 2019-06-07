<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.fatec.aula.dominio.EntidadeDominio"%>
<%@page import="br.edu.fatec.aula.dominio.Funcionario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>:::: CADASTRO FUNCIONARIO::::</title>


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<style>
body {
	background-color: #0080FF;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 20px;
	margin-bottom: 30px;
}

.centro {
	text-align: center;
}

.caixa {
	border: 2px none;
	border-radius: 15px;
	padding-top: 10px;
	height: 600px;
	background-color: #FFFFFF;
}

#tam {
	width: 250px;
	height: 80px;
	margin-left: 20px;
	/* background-color: red; */
	position: relative;
	display: inline-block;
}

#botaoSalvar {
	width: 600px;
	height: 100px;
	/* 	background-color: blue; */
	margin-left: 200px;
}

#tabela {
	width: 600px;
	height: 100px;
}
</style>

</head>
<body>

	<div class="caixa">

		<form action="ControleFuncionario" method="POST">

			<div class="form-group" id="tam">
				<label for="txtMatricula">Matricula:</label><br> <input
					type="text" id="txtMatricula" name="txtMatricula" /> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtNome">Nome:</label> <br> <input type="text"
					id="txtNome" name="txtNome" /> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtCPF">CPF:</label><br> <input type="text"
					id="txtCPF" name="txtCPF" /> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtEmail">Email:</label><br> <input type="email"
					id="txtEmail" name="txtEmail" /> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtSenha">Senha:</label><br> <input type="password"
					id="txtSenha" name="txtSenha" /> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtCargo">Cargo:</label> <br> <select id="txtCargo"
					name="txtCargo">
					<option value=""></option>
					<option value="1">Desenvolvedor</option>
					<option value="2">Administrador</option>
					<option value="3">Analista</option>
					<option value="4">Aprendiz</option>
					<option value="5">Engenheiro</option>
					<option value="6">Assistente</option>
				</select> <br />
			</div>

			<div class="form-group" id="tam">
				<label for="txtSetor">Setor:</label> <br> <select id="txtSetor"
					name="txtSetor">
					<option value=""></option>
					<option value="1">Desenvolvimento</option>
					<option value="2">Teste</option>
					<option value="3">Engenharia</option>
					<option value="4">Administra��o</option>
				</select> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtRegional">Regional:</label></br> <select id="txtRegional"
					name="txtRegional">
					<option value=""></option>
					<option value="1">Norte</option>
					<option value="2">Nordeste</option>
					<option value="3">Centro-Oeste</option>
					<option value="4">Sudeste</option>
					<option value="5">Sul</option>
				</select> <br />
			</div>
			<div class="form-group" id="botaoSalvar">
				<input type="submit" class="btn btn-success" id="operacao"
					name="operacao" value="SALVAR_FUNCIONARIO" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="BUSCAR_FUNCIONARIO" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="ALTERAR_FUNCIONARIO" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="EXCLUIR_FUNCIONARIO" />
			</div>

			<%
				String msg = (String) session.getAttribute("msg");
				if (msg != null) {
					out.print(msg);
				}
				session.removeAttribute("msg");
			%>
		</form>
	</div>


	<TABLE BORDER="5" WIDTH="50%" CELLPADDING="4" CELLSPACING="3">
		<TR>
			<TH COLSPAN="3"><BR>
				<H3>FUNCIONARIOS</H3></TH>
		</TR>

		<TR>
			<TH>ID:</TH>
			<TH>NOME</TH>
			<TH>CPF:</TH>
			<TH>EMAIL:</TH>
		</TR>


		<%
	
		List<EntidadeDominio> entidades = (List<EntidadeDominio>) request.getAttribute("resultado");
			
			if (entidades != null) {			
				StringBuilder sbRegistro = new StringBuilder();
				StringBuilder sbLink = new StringBuilder();

				
					for (int i = 0; i < entidades.size(); i++) {
						Funcionario p =  (Funcionario)entidades.get(i);
						sbRegistro.setLength(0);
						sbLink.setLength(0);

						//	<a href="nome-do-lugar-a-ser-levado">descri��o</a>

						sbRegistro.append("<TR ALIGN='CENTER'>");			

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getId());			
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getNome());
						sbRegistro.append("</TD>");

						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getCpf());
						sbRegistro.append("</TD>");
						
						sbRegistro.append("<TD>");
						sbRegistro.append(sbLink.toString());
						sbRegistro.append(p.getEmail());						
						sbRegistro.append("</TD>");

						sbRegistro.append("</TR>");

						out.print(sbRegistro.toString());

					}
				}

			
		%>


</body>
</html>