<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Atualizar Senha</title>
</head>
<body>
	<p><c:out value="${mensagem}"/></p>
	<form action="" method="post">
		<input type=text name="email" placeholder="Email"><br>
		<input type="text" name="novaSenha" placeholder="Nova Senha"><br>
		<input type="text" name="novaSenhaRepetida" placeholder="Repetir Senha"><br>
		<button type="submit">Atualizar senha</button>
	</form>
</body>
</html>