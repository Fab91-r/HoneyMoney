<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@page import="manage.GestioneSaldo"%>
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
		ArrayList<String> listaCategorie = (ArrayList<String>) request.getAttribute("listaCategorie");
	%>
	<form action="modify" method="POST">
		<table>
			<tr>
				<th>Categoria</th>
			</tr>
			<%
				for (String singolaCategoria : listaCategorie) {
			%>
			<tr style="background-color:#0099ff;">
				<th style="text-align: left">
				<input type="radio" name="categoria" value="<%=singolaCategoria.getId()%>"><%=singolaCategoria%></th>
			</tr>
			<%
				}
			%>

		</table>
  <div class="container">
    <p>Modifica la categoria selezionata</p>
    <label for="categoria"><b>Data</b></label>
    <input type="text" placeholder=" Inserisci categoria" name="data" required>
	<br>
	<br>
    <hr>
     <input type="submit" value="Modifica">
</form>
	<br>
	<br>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
	<br>
</body>
</html>