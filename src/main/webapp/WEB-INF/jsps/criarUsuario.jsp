<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="jakarta.tags.core" prefix="c" %>      
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Conta</title>
</head>
<body>
	<p><c:out value="${mensagem}"/></p>
	<form action="criarUsuario" method="post">
		<label>Email:</label><br>
		<input type="text" name="email"><br>
		<label>Senha:</label><br>
		<input type="text" name="senha"><br>
		<button type="submit">Criar</button>
	</form>
</body>
</html>