<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="insert" method= "POST">
  <div class="container">
    <p>Inserisci una nuova transazione</p>
    <label for="data"><b>Data&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
    <input type="date" placeholder="Inserisci data" name="data" required>
	<br>
	<br>
    <label for="descrizione"><b>Descrizione</b></label>
    <input type="text" placeholder="Inserisci descrizione" name="descrizione" required>
    <br>
    <br>
    <label for="categoria"><b>Categoria&nbsp&nbsp</b></label>
    <input type="text" placeholder="Inserisci categoria" name="categoria" required>
    <br>
    <br>
    <label for="importo"><b>Importo&nbsp&nbsp&nbsp</b></label>
    <input type="text" placeholder="Inserisci importo" name="importo" required>
    <br>
    <br>
    <hr>
     <input type="submit" value="Inserisci">
</form>
</body>
</html>