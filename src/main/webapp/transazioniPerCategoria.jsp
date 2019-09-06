<%@page import="java.util.ArrayList"%>
<%@page import="models.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza l'elenco delle categorie</title>
</head>
<body>
	<%
		ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) session.getAttribute("listaCategorie");
	%>
	<h2>Seleziona la categoria</h2>
	<form action="viewCat"  method="POST">
		<table>
			<%
				for (Categoria singolaCategoria : listaCategorie) {
			%>

			<tr style="background-color: #ffff00;">
				<th style="text-align: center"><input type="radio"
					name="categoria" value="<%=singolaCategoria.getCategoria()%>" required><%=singolaCategoria.getCategoria()%></th>
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
