<%@page import="br.edu.fatec.aula.dominio.Resultado"%>
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
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
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

<%
	Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
%>

<body>

	<div class="caixa">

		<form action="SalvarFuncionario" method="POST">

			<div class="form-group" id="tam">
				<label for="txtMatricula">Matricula:</label> <br>
				<%
					out.print("<input type='text' id='txtMatricula' name='txtMatricula' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getMatricula() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>

			<div class="form-group" id="tam">
				<label for="txtNome">Nome:</label> <br>
				<%
					out.print("<input type='text' id='txtNome' name='txtNome' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getNome() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>

			<div class="form-group" id="tam">
				<label for="txtCPF">CPF:</label><br>
				<%
					out.print("<input type='text' id='txtCPF' name='txtCPF' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getCpf() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>

			<div class="form-group" id="tam">
				<label for="txtEmail">Email:</label><br>
				<%
					out.print("<input type='text' id='txtEmail' name='txtEmail' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getEmail() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>

			<div class="form-group" id="tam">
				<label for="txtSenha">Senha:</label><br>
				<%
					out.print("<input type='password' id='txtSenha' name='txtSenha' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getSenha() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>

			<div class="form-group" id="tam">
				<label for="txtCargo">Cargo:</label> <br> 
				<select id="txtCargo"
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
					<option value="4">Administração</option>
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
					name="operacao" value="SALVAR" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="CONSULTAR" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="ALTERAR" /> <input type="submit"
					class="btn btn-success" id="operacao" name="operacao"
					value="EXCLUIR" />
			</div>

			<%
				Resultado msg = (Resultado) session.getAttribute("msg");
				if (msg == null) {
					out.print("Funcionario Salvo com sucesso.");
				}else{
					out.print(msg.getMsg());
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
			<TH>MATRICULA:</TH>
			<TH>NOME</TH>
			<TH>CPF:</TH>
			<TH>EMAIL:</TH>
			<TH>ACTION:</TH>
		</TR>

		<%
			List<EntidadeDominio> entidades = (List<EntidadeDominio>) request.getAttribute("resultado");

			if (entidades != null) {
				StringBuilder sbRegistro = new StringBuilder();
				StringBuilder sbLink = new StringBuilder();

				for (int i = 0; i < entidades.size(); i++) {
					Funcionario p = (Funcionario) entidades.get(i);
		%>

		<TR ALIGN='CENTER'>
			<td><%=p.getMatricula()%></td>
			<td><%=p.getNome()%></td>
			<td><%=p.getCpf()%></td>
			<td><%=p.getEmail()%></td>

			<td>
				<div class="icon">
					<a href="ControleFuncionario?id=<%=p.getId()%>&op=ed"><i
						class="material-icons" style="color: green;">create</i></a>
				</div>
				<div class="icon">
					<a href="ControleFuncionario?id=<%=p.getId()%>&op=ex"><i class="material-icons" style="color: green;">clear</i></a>
				</div>
			</td>
		</TR>
		<%
			}
			}
		%>
	
</body>
</html>