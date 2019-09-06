<%@page import="java.util.ArrayList"%>
<%@page import="models.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Elimina categoria</title>
</head>
<body>
	<%
		ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) request.getAttribute("listaCategorie");
	%>
	<h2>Seleziona la categoria da eliminare<h2>
	<h3>Le categorie di default (Trasporti, Bollette, Alimentari, Altro) non possono essere eliminate</h3>
	<form action="deleteCat" method="POST">
		<table>
			<tr>
				<th>Categoria</th>
			</tr>
			<%
				for (Categoria singolaCategoria : listaCategorie) {
			%>
			<% if(singolaCategoria.getCategoria().equals("trasporto") || singolaCategoria.getCategoria().equals("bollette") ||
					singolaCategoria.getCategoria().equals("alimentari") || singolaCategoria.getCategoria().equals("altro")) { %>
			<tr style="background-color:#ffff00;">
				<th style="text-align: left">
				<input type="radio" name="categoria" value="<%=singolaCategoria.getId()%>" disabled><%=singolaCategoria.getCategoria()%></th>
			</tr>
			<%} if(!(singolaCategoria.getCategoria().equals("trasporto") || singolaCategoria.getCategoria().equals("bollette") ||
					singolaCategoria.getCategoria().equals("alimentari") || singolaCategoria.getCategoria().equals("altro")))  { %>
			<tr style="background-color:#ffff00;">
			<th style="text-align: left">
			<input type="radio" name="categoria" value="<%=singolaCategoria.getId()%>"><%=singolaCategoria.getCategoria()%></th>
		</tr>
			<% }}%>
			
		</table>
	<br>
	<br>
    <hr>
    <% if(listaCategorie.size() == 4) { %>
     <input type="submit" value="Cancella" disabled>
     <%} %>
     <% if(listaCategorie.size() > 4) { %>
     <input type="submit" value="Cancella" >
     <%} %>
</form>
	<br>
	<br>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
	<br>
</body>
</html>