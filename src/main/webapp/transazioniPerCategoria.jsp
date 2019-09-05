<%@page import="java.util.ArrayList"%>
<%@page import="models.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza le categorie</title>
</head>
<body>
	<%
		ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) request.getAttribute("listaCategorie");
	%>
	<form action="benvenuto.jsp">
		<table>
			<tr>
				<th>Elenco delle categorie</th>
			</tr>
			<%
				for (Categoria singolaCategoria : listaCategorie) {
			%>

			<tr style="background-color: #0099ff;">
				<th style="text-align: center"><input type="radio"
					name="categoria" value="<%=singolaCategoria.getId()%>"><%=singolaCategoria.getCategoria()%></th>
			</tr>
			<%
				}
			%>
		</table>
		<br> 
		<br>
		<hr>
		<input type="submit" value="Seleziona">
	</form>
	<br>
	<br>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
</body>
</html>
