<!DOCTYPE html>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bem Vindo</title>
</head>
<body>

	<%@include file="menu.jsp"%>
	<br>
	Bem Vindo <% Usuario usuario = (Usuario)request.getSession().getAttribute("usuAutenticado");
			  	 out.print(usuario.getNome() + "!");
			  %>
</body>
</html>