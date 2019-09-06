<%@page import="java.util.ArrayList"%>
<%@page import="models.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica categoria</title>
</head>
<body>
	<%
		ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) request.getAttribute("listaCategorie");
	%>
	<h2>Seleziona la categoria da modificare</h2>
	<h3>Le categorie di default (Trasporti, Bollette, Alimentari,
		Altro) non possono essere modificate</h3>
	<form action="modifyCat" method="POST">
		<table>
			<tr>
				<th>Categoria</th>
			</tr>
			<%
				for (Categoria singolaCategoria : listaCategorie) {
			%>
			<%
				if (singolaCategoria.getCategoria().equals("trasporto")
							|| singolaCategoria.getCategoria().equals("bollette")
							|| singolaCategoria.getCategoria().equals("alimentari")
							|| singolaCategoria.getCategoria().equals("altro")) {
			%>
			<tr style="background-color: #ffff00;">
				<th style="text-align: left"><input type="radio"
					name="categoria" value="<%=singolaCategoria.getId()%>" disabled><%=singolaCategoria.getCategoria()%></th>
			</tr>
			<%
				}
					if (!(singolaCategoria.getCategoria().equals("trasporto")
							|| singolaCategoria.getCategoria().equals("bollette")
							|| singolaCategoria.getCategoria().equals("alimentari")
							|| singolaCategoria.getCategoria().equals("altro"))) {
			%>
			<tr style="background-color: #ffff00;">
				<th style="text-align: left"><input type="radio"
					name="categoria" value="<%=singolaCategoria.getId()%>"><%=singolaCategoria.getCategoria()%></th>
			</tr>
			<%
				}
				}
			%>

		</table>


		<div class="container">
			<p>Modifica la categoria selezionata</p>

			<%
				if (listaCategorie.size() == 4) {
			%>
			<label for="categoria"><b>Categoria</b></label> <input type="text"
				placeholder=" Inserisci categoria" name="categoriaNuova" required
				disabled> <br> <br>
			<hr>
			<input type="submit" value="Modifica" disabled>
			<%
				}
			%>
			<%
				if (listaCategorie.size() > 4) {
			%>
			<div class="container">
				<p>Modifica la categoria selezionata</p>

				<label for="categoria"><b>Categoria</b></label> <input type="text"
					placeholder=" Inserisci categoria" name="categoriaNuova" required>
				<br> <br>
				<hr>
				<input type="submit" value="Modifica">
				<%
					}
				%>
			
	</form>
	<br>
	<br>
	<hr>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
	<br>
</body>
</html>