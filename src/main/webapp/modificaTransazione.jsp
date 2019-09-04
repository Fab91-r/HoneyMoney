<%@page import="java.util.ArrayList"%>
<%@page import="models.Transazione"%>
<%@page import="manage.GestioneSaldo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica transazione</title>
</head>
<body>
<%String username = (String)session.getAttribute("user"); %>
<%GestioneSaldo.getSaldoCorrente(username);%>
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
  <div class="container">
    <p>Modifica la transazione selezionata</p>
    <label for="data"><b>Data</b></label>
    <input type="date" placeholder="  Inserisci data" name="data" required>
	<br>
	<br>
    <label for="descrizione"><b>Descrizione</b></label>
    <input type="text" placeholder="  Inserisci descrizione" name="descrizione" required>
    <br>
    <br>
    <label for="categoria"><b>Categoria</b></label>
    <input type="text" placeholder="  Inserisci categoria" name="categoria" required>
    <br>
    <br>
    <label for="importo"><b>Importo</b></label>
    <input type="text" placeholder="  Inserisci importo" name="importo" required>
    <input type="radio" name="scelta" value="1" required> Entrata
    <input type="radio" name="scelta" value="-1" required> Spesa
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


