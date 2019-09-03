<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica transazione</title>
</head>
<body>
	<%
		ArrayList<Transazione> listaTransazioni = (ArrayList<Transazione>) request.getAttribute("listaTransazioni");
	%>
	<form action="modify" method="POST">
		<table>
			<tr>
				<th>Data</th>
				<th>Descrizione</th>
				<th>Categoria</th>
				<th>Importo</th>
			</tr>
			<%
				for (Transazione singolaTransazione : listaTransazioni) {
			%>
			<tr>
				<th style="text-align: left">
				<input type="radio" name="transazione" value="<%=singolaTransazione.getId()%>"><%=singolaTransazione.getData()%></th>
				<th><%=singolaTransazione.getDescrizione()%></th>
				<th><%=singolaTransazione.getCategoria()%></th>
				<th><%=singolaTransazione.getImporto()%></th>
			</tr>
			<%
				}
			%>
		</table>
		<input type="submit" value="Modifica">
	</form>
	<br>
	<br>
	<form action="benvenuto.jsp" align="center">
		<input type="submit" value="Torna alla home">
	</form>
	<br>
</body>
</html>


