<%@page import="br.edu.fatec.aula.dominio.Resultado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.edu.fatec.aula.dominio.EntidadeDominio"%>
<%@page import="br.edu.fatec.aula.dominio.Funcionario"%>
<%@page import="br.edu.fatec.aula.dominio.Usuario"%>
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

<script>
	function validarSenha() {

		var senha1 = document.getElementsByName('txtSenha')[0].value;
		var senha2 = document.getElementsByName('txtSenha1')[0].value;
		if (senha1 != senha2) {
			alert("SENHAS DIFERENTES!\\nFAVOR DIGITAR SENHAS IGUAIS");
			return false;
		}
		return true;

	}
</script>


<style>
body {
	background-color: #0080FF;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 20px;
	margin-bottom: 30px;
}

.limpar {
	margin-left: 90%;
	margin-top: -380px;
	width: 100px;
	height: 40px;
	width: 100px;
}

.msgErro {
	height: 40px;
	width: 300px;
	margin-top: 300px;
}

.centro {
	text-align: center;
}

.caixa {
	border: 2px none;
	border-radius: 15px;
	padding-top: 10px;
	height: 1500px;
	background-color: #FFFFFF;
}

#tam {
	width: 250px;
	height: 80px;
	margin-left: 20px;
	position: relative;
	display: inline-block;
}

#botaoSalvar {
	width: 600px;
	height: 100px;
	margin-left: 200px;
}

#tabela {
	width: 600px;
	height: 100px;
}
</style>

</head>

<%
	Funcionario funcionario = (Funcionario) request.getSession().getAttribute("funcionario");
%>

<body>

	<div class="caixa">

		<form action="ControleFuncionario" method="POST"
			onsubmit="return validarSenha();">

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
				<label for="txtSenha">Confirmar Senha:</label><br>
				<%
					out.print("<input type='password' id='txtSenha1' name='txtSenha1' value=");
					if (funcionario != null)
						out.print("'" + funcionario.getSenha() + "' /><br>");
					else
						out.print(" ><br>");
				%>
			</div>
			<div class="form-group" id="tam">
				<label for="txtCargo">Cargo:</label> <br> <select id="txtCargo"
					name="txtCargo">

					<%
						out.print("<option value=");
						if (funcionario != null) {
							out.print("'" + funcionario.getCargo().getId() + "'>" + funcionario.getCargo().getDescricao()
									+ "</option>");
						} else {
							out.print("''></option>");
						}
					%>
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
					<%
						out.print("<option value=");
						if (funcionario != null) {
							out.print("'" + funcionario.getSetor().getId() + "'>" + funcionario.getSetor().getNome() + "</option>");
						} else {
							out.print("''></option>");
						}
					%>
					<option value="1">Desenvolvimento</option>
					<option value="2">Teste</option>
					<option value="3">Engenharia</option>
					<option value="4">Administração</option>
				</select> <br>
			</div>

			<div class="form-group" id="tam">
				<label for="txtRegional">Regional:</label></br> <select id="txtRegional"
					name="txtRegional">
					<%
						out.print("<option value=");
						if (funcionario != null) {
							out.print("'" + funcionario.getRegional().getId() + "'>" + funcionario.getRegional().getNome()
									+ "</option>");
						} else {
							out.print("''></option>");
						}
					%>
					<option value="1">Norte</option>
					<option value="2">Nordeste</option>
					<option value="3">Centro-Oeste</option>
					<option value="4">Sudeste</option>
					<option value="5">Sul</option>
				</select> <br />
			</div>

			<%
				Usuario usuarioAuten = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
			%>


			<input type='hidden' id='txtCadastradoPor' name='txtCadastradoPor'
				value="<%=1%>">


			<%
				if (funcionario != null) {
					out.print("<input type='hidden' id='txtIdFuncionario' name='txtIdFuncionario' value=");
					if (funcionario.getId() > 0)
						out.print("'" + funcionario.getId() + "'>");
					else
						out.print(" ><br>");
				}
			%>


			<div class="form-group" id="botaoSalvar">
				<input type="submit" class="btn btn-success" id="operacao"
					name="operacao" onclick="ConfirmaSenha()" value="SALVAR" /> <input
					type="submit" class="btn btn-success" id="operacao" name="operacao"
					value="CONSULTAR" /> <input type="submit" class="btn btn-success"
					id="operacao" name="operacao" value="ALTERAR" /> <input
					type="submit" class="btn btn-success" id="operacao" name="operacao"
					value="EXCLUIR" />
			</div>
			<div class="limpar">
				<a href="FormFuncionario.jsp">LIMPAR</a>
			</div>
			<div class="msgErro">

				<%
					request.getSession().removeAttribute("funcionario");
					Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
					if (resultado != null) {
						out.print(resultado.getMsg());
						//request.getSession().removeAttribute("resultado");
					}
				%>
			</div>
		</form>

		<%
			if (request.getSession().getAttribute("resultado") != null) {
				Funcionario fun = (Funcionario) resultado.getEntidades().get(0);
				if (fun.getCargo().getDescricao() != null) {
		%>

		<TABLE BORDER="5" WIDTH="50%" CELLPADDING="4" CELLSPACING="3"
			style="margin-top: 50px;">
			<TR>
				<TH COLSPAN="3"><BR>
					<H3>FUNCIONARIOS</H3></TH>
			</TR>

			<TR>
				<TH>MATRICULA:</TH>
				<TH>NOME</TH>
				<TH>CPF:</TH>
				<TH>EMAIL:</TH>
				<TH>CARGO:</TH>
				<TH>SETOR:</TH>
				<TH>REGIONAL:</TH>
				<TH>ACTION:</TH>
			</TR>

			<%
				for (int i = 0; i < resultado.getEntidades().size(); i++) {
							Funcionario p = (Funcionario) resultado.getEntidades().get(i);
			%>

			<TR ALIGN='CENTER'>
				<td><%=p.getMatricula()%></td>
				<td><%=p.getNome()%></td>
				<td><%=p.getCpf()%></td>
				<td><%=p.getEmail()%></td>
				<td><%=p.getCargo().getDescricao()%></td>
				<td><%=p.getSetor().getNome()%></td>
				<td><%=p.getRegional().getNome()%></td>

				<td>
					<div class="icon">
						<a href="ControleFuncionario?id=<%=p.getId()%>&operacao=PERFIL"><i
							class="material-icons" style="color: green;">eject</i></a>
					</div>
				</td>
			</TR>
			<%
				}
					}
				}
				request.getSession().invalidate();
			%>
			</div>
</body>
</html>