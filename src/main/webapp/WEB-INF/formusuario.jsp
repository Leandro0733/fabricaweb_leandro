<!DOCTYPE html>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
</head>
<body>
	<%@include file="menu.jsp"%>
	<%
	Usuario u = (Usuario)request.getAttribute("usuario");
	%>
	<form action="usucontroller.do" method="post">
		Id: <input type="number" readonly="readonly" name="id" value="<%=u.getId()%>"/>
		Nome: <input type="text" name="nome" value="<%=u.getNome()%>"/>
		Login: <input type="text" name="login" value="<%=u.getLogin()%>"/>
		Senha: <input type="password" name="senha" value="<%=u.getSenha()%>"/>
		<input type="submit" value="Salvar"/>		
	</form>

</body>
</html>