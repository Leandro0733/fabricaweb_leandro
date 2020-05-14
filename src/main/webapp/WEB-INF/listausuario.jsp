<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Lista de Usuários</title>

<script type="text/javascript">
function confirmaExclusao(id) {
	if(window.confirm('Confirma a exclusão?')){
		location.href= "usucontroller.do?acao=excluir&id="+id;
	}
}

function novo() {
	location.href='usucontroller.do?acao=cadastrar';
}
</script>

</head>
<body>
	<%@include file="menu.jsp"%>
	<%
	    @SuppressWarnings("unchecked")
		List<Usuario> lista = (List<Usuario>)request.getAttribute("listausuario");
	%>
	<table border="1">
	<tr> <th> Id </th> <th> Nome </th> <th> Ação </th> </tr>
		
	<% for(Usuario u: lista){%>
		<tr>
		<td><%=u.getId()%></td> 
		<td><%=u.getNome()%></td>
		<td> <a href="javascript:confirmaExclusao(<%=u.getId()%>)"> Excluir </a> |
			 <a href="usucontroller.do?acao=alterar&id=<%=u.getId()%>"> Alterar </a>
		</td>
		</tr>
	<%} %>
	</table>
	
	<input type="button" value="Novo" onclick="javascript:novo()"/>
	
</body>
</html>