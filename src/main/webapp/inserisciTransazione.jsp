<%@page import="models.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manage.Gestione"%>
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
 <% String data = Gestione.getDataAttuale(); %>
     <h2>Inserisci una nuova transazione</h2>
<form action="insert" method= "POST">
  <div class="container">
    <label for="data"><b>Data</b></label>
    <input type="date" placeholder=" Inserisci data" name="data" min="1970-01-01" max="<%=data%>" required>
	<br>
	<br>
    <label for="descrizione"><b>Descrizione</b></label>
    <input type="text" placeholder=" Inserisci descrizione" name="descrizione" required>
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
    <input type="text" placeholder=" Inserisci importo" name="importo" required>
    <input type="radio" name="scelta" value="1" required> Entrata
    <input type="radio" name="scelta" value="-1" required> Spesa
    <br>
    <br>
    <hr>
     <input type="submit" value="Inserisci">
</form>
<br>
<br>
 <form action="benvenuto.jsp">
 <input type="submit" value="Torna alla home">
  </form>
  <br>
</body>
</html>