    <%@page import="manage.GestioneSaldo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String username = (String)session.getAttribute("user"); %>
<%GestioneSaldo.getSaldoCorrente(username);%>
<form action="insert" method= "POST" align="center">
  <div class="container">
    <p>Inserisci una nuova transazione</p>
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
     <input type="submit" value="Inserisci">
</form>
</body>
</html>