<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@page import="manage.GestioneSaldo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cancella transazione</title>
</head>
<body>
<%String username = (String)session.getAttribute("user"); %>
<%GestioneSaldo.getSaldoCorrente(username);%>
	<%
		ArrayList<Transazione> listaTransazioni = (ArrayList<Transazione>) request.getAttribute("listaTransazioni");
	%>
	<form action="delete" method="POST">
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
			<% if (singolaTransazione.getImporto()>= 0) {%>
			<tr style="background-color:#0099ff;">
				<th style="text-align: left">
				<input type="radio" name="transazione" value="<%=singolaTransazione.getId()%>"><%=singolaTransazione.getData()%></th>
				<th><%=singolaTransazione.getDescrizione()%></th>
				<th><%=singolaTransazione.getCategoria()%></th>
				<th><%=singolaTransazione.getImporto()%></th>
			</tr>
			<%
				}
			%>
			<% if (singolaTransazione.getImporto() < 0){ %>
			<tr style="background-color:#ff5050;">
				<th style="text-align: left">
				<input type="radio" name="transazione" value="<%=singolaTransazione.getId()%>"><%=singolaTransazione.getData()%></th>
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
	<br>
     <input type="submit" value="Cancella">
</form>
	<br>
	<br>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
	<br>
</body>
</html>