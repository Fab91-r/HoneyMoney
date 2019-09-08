<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizzazione di tutte le transazioni</title>
</head>
<body>
	<%
		ArrayList<Transazione> listaTransazioni = (ArrayList<Transazione>) request.getAttribute("listaTransazioni");
	%>
	<h2>Elenco di tutte le transazioni effettuate</h2>
	
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
			<%
				if (singolaTransazione.getImporto() >= 0) {
			%>
			<tr style="background-color: #0099ff;">
				<th><%=singolaTransazione.getData()%></th>
				<th><%=singolaTransazione.getDescrizione()%></th>
				<th><%=singolaTransazione.getCategoria()%></th>
				<th><%=singolaTransazione.getImporto()%></th>
			</tr>
			<%
				}
			%>
			<%
				if (singolaTransazione.getImporto() < 0) {
			%>
			<tr style="background-color: #ff5050;">
				<th><%=singolaTransazione.getData()%></th>
				<th><%=singolaTransazione.getDescrizione()%></th>
				<th><%=singolaTransazione.getCategoria()%></th>
				<th><%=singolaTransazione.getImporto()%></th>
			</tr>
			<%
				}
				}
			%>

		</table>

	<br>
	<hr>
	<p> Genera un file Excel con tutte le transazioni effettuate</p>
	<form action="excel" method="POST">
		<input type="submit" value="Genera File">
	</form>
	<br>
	<hr>
	<form action="benvenuto.jsp">
	<input type="submit" value="Torna alla Home">
	</form>
</body>
</html>