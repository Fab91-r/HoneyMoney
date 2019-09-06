<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@page import="models.Categoria"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visualizza le transazioni per categoria</title>
</head>
<body>
	<%
		ArrayList<Transazione> listaTransazioniPerCategoria = (ArrayList<Transazione>) request.getAttribute("lista");
	
	   ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) session.getAttribute("listaCategorie");
	  
	%>
	<h2>Elenco delle transazioni per categoria</h2>
	<form>
		<table>
			<tr>
				<th>Data</th>
				<th>Descrizione</th>
				<th>Categoria</th>
				<th>Importo</th>
			</tr>
			<%
				for (Transazione singolaTransazione : listaTransazioniPerCategoria) {
			%>
			<% if (singolaTransazione.getImporto()>= 0) {%>
			<tr style="background-color: #0099ff;">
				<th><%=singolaTransazione.getData()%></th>
				<th><%=singolaTransazione.getDescrizione()%></th>
				<th><%=singolaTransazione.getCategoria()%></th>
				<th><%=singolaTransazione.getImporto()%></th>
			</tr>
			<%
				}
			%>
			<% if (singolaTransazione.getImporto() < 0){ %>
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
		<br> <br>
		<hr>
	</form>
<form action="transazioniPerCategoria.jsp">

		<input type="submit" value="Scegli un'altra categoria">
	</form>
	<br>
	<br>
	<form action="benvenuto.jsp">
		<input type="submit" value="Torna alla home">
	</form>
</body>
</html>