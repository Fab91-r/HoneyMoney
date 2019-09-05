<%@page import="models.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento transazione</title>
</head>
<body>
<%String username = (String)session.getAttribute("user"); %>
 <% ArrayList<Categoria> listaCategorie = (ArrayList<Categoria>) request.getAttribute("listaCategorie"); %>
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
    <B>Categoria</B>
      <select name="categoria">
    <% for (Categoria singolaCategoria : listaCategorie) { %>
 
  <option value="<%=singolaCategoria.getCategoria()%>"><%=singolaCategoria.getCategoria()%></option>
<%} %>
</select>
<br>
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
<br>
<br>
 <form action="benvenuto.jsp" align="center">
 <input type="submit" value="Torna alla home">
  </form>
  <br>
</body>
</html>