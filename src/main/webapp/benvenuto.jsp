<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Benvenuto <%=session.getAttribute("user")%> ! </h1>
  <% if(request.getAttribute("messaggio") != null) {%>
  <%String messaggio = (String)request.getAttribute("messaggio");  %>
  <p style="color:red;"><%=messaggio %> </p>
  <%} %>
<h2>Il tuo saldo &egrave</h2>
<p>Le spese effettuate in questo mese sono di &#8364</p>
<p>Le entrate in questo mese sono di &#8364</p>
<p>Gestisci Transazioni:</p>
<form action="transaction" method="POST">
<p>Scegli tra le opzioni:</p>
  <input type="radio" name="scelta" value="1"> Inserisci<br>
  <input type="radio" name="scelta" value="2"> Modifica <br>
  <input type="radio" name="scelta" value="3"> Elimina
  <br>
  <br>
  <input type="submit" value="Esegui">
</form> 

<form action="view" method="POST">
<p>Scegli tra le opzioni:</p>
  <input type="radio" name="scelta" value="1"> Visualizza tutte le transazioni effettuate<br>
  <input type="radio" name="scelta" value="2"> Visualizza le transazioni positive <br>
  <input type="radio" name="scelta" value="3"> Visualizza le transazioni negative
  <br>
  <br>
  <input type="submit" value="Esegui">
</form>
</body>
</html>