<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="jakarta.tags.core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bem vindo</title>
</head>
<body>
	<h1>Olá, seja bem vindo <span style="color: blue; text-transform: uppercase;"><c:out value="${userName}"/></span></h1>
</body>
</html>