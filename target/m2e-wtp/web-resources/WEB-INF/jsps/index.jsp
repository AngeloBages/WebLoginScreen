<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Screen</title>
</head>
<body>
	<h1>Plataforma Virtual</h1><br>
	
	<p><c:out value="${mensagem}"/></p>
	<form action="" method="POST">
		<label>Email:</label><br>
		<input type="text" name="email"><br>
		<label>Senha:</label><br>
		<input type="text" name="senha"><br>
		<button type="submit">Logar</button>
	</form>
	<p><a href="${pageContext.request.servletContext.contextPath}/?action=CriarUsuarioServlet">Criar Conta</a></p>
	<p><a href="${pageContext.request.servletContext.contextPath}/?action=AtualizarSenhaServlet">Esqueci a senha...</a></p>
</body>
</html>