<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<!-- BOOTSTRAP -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-social/bootstrap-social.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- JQUERY -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>

<title>Login</title>

<style>
body {
	background-color: #0080FF;
	margin-left: 30%;
	margin-right: 30%;
	margin-top: 80px;
	margin-bottom: 30px;
}

.centro {
	text-align: center;
}

.caixa {
	padding-top: 20px;
	border: 2px none;
	border-radius: 15px;
	width: auto;
	height: 200px;
	background-color: #FFFFFF;
}
</style>

</head>
<body>

	<div class="caixa">
		<div
			style="position: absolute; left: 50%; transform: translateX(-50%);">

			<form action="Autenticacao" method="POST">

				<div class="form-group">
					<label for="Login">Login</label><br> <input type="text"
						class="form-control" name="txtLogin" id="txtLogin" placeholder="Login">
				</div>

				<div class="form-group">
					<label for="password">Senha</label><br> <input type="password"
						class="form-control" name="txtSenha" id="txtSenha"
						placeholder="Senha">
				</div>

				<br>

				<div class="centro">
					<button type="submit" class="btn btn-primary" id="centro">Entrar</button>
					<br>
				</div>
			</form>
		</div>
	</div>

</body>
</html>