<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserimento categoria</title>
</head>
<body>
<%String username = (String)session.getAttribute("user"); %>
<h2>Inserisci una nuova categoria</h2>
<form action="insertCat" method= "POST" align="center">
  <div class="container">
    
    <label for="categoria"><b>Categoria</b></label>
    <input type="text" placeholder=" Inserisci categoria" name="categoria" required>
	<br>
	<br>
    <hr>
     <input type="submit" value="Inserisci">
</form>
<br>
<br>
<hr>
 <form action="benvenuto.jsp" align="center">
 <input type="submit" value="Torna alla home">
  </form>
  <br>
</body>
</html>