    <%@page import="manage.Gestione"%>
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
<h1>Benvenuto <%=username%> ! </h1>

  <% if(request.getAttribute("messaggio") != null) {%>
  <%String messaggio = (String)request.getAttribute("messaggio");  %> 
  <p style="color:#00cc00;"><%=messaggio %> </p>
  <%} %>
  
  <h2>Il tuo saldo &egrave
  <%int saldo =Gestione.getSaldoCorrente(username);%>
  <% if (saldo <0) { %>
<font color="#ff5050"><%=saldo%></font>
<% } %>
  <% if (saldo == 0) { %>
<font color="#00cc00"><%=saldo%></font>
<% } %>
<%if (saldo > 0){ %>
<font color="#0099ff"><%=saldo%></font> 
<% } %>
 &#8364</h2>
<%int saldoSpese =Gestione.getSaldoNegativoMeseCorrente(username);%>
<%int saldoEntrate =Gestione.getSaldoPositivoMeseCorrente(username);%>
<p>Le spese effettuate in questo mese sono di &#8364 <%=saldoSpese %></p>
<p>Le entrate in questo mese sono di &#8364 <%=saldoEntrate %></p>
<p>Gestisci le transazioni:</p>
<form action="transaction" method="POST">
  <input type="radio" name="scelta" value="1"> Inserisci<br>
  <input type="radio" name="scelta" value="2"> Modifica <br>
  <input type="radio" name="scelta" value="3"> Elimina
  <br>
  <br>
  <input type="submit" value="Esegui">
</form> 
<hr>
<p>Gestisci le categorie delle transazioni:</p>
<form action="categorie" method="POST">
  <input type="radio" name="scelta" value="1"> Visualizza<br>
  <input type="radio" name="scelta" value="2"> Inserisci<br>
  <input type="radio" name="scelta" value="3"> Modifica <br>
  <input type="radio" name="scelta" value="4"> Elimina
  <br>
  <br>
  <input type="submit" value="Esegui">
</form> 
<hr>
<form action="view" method="POST">
<p>Visualizza le transazioni effettuate:</p>
  <input type="radio" name="scelta" value="1"> Transazioni totali <br>
  <input type="radio" name="scelta" value="2"> Transazioni positive <br>
   <input type="radio" name="scelta" value="3"> Transazioni negative <br>
  <input type="radio" name="scelta" value="4"> Transazioni per categoria
  <br>
  <br>
  <input type="submit" value="Esegui">
</form>
<br>
  <br>
  <form action="logout" method="POST">
  <input type="submit" value="Logout">
</form>
</body>
</html>