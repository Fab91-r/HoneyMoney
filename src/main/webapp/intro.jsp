<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Introduzione all'applicazione</title>

</head>
<body>
<h1 style="color:red;" align="center">HoneyMoney</h1>
<h2 style="color:orange;" align="center">Tieni traccia di tutte le tue transazioni economiche</h2>

<form action="login" method = "POST" align="center">
  <div class="container">
    <h2>Accedi all'applicazione</h2>
    <p>Inserisci i tuoi dati:<p>
  

    <label for="user"><b>Username</b></label>
    <input type="text" placeholder="Inserisci Username" name="user" required>
    <br>
    <br>
    <label for="pass"><b>Password&nbsp</b></label>
    <input type="password" placeholder="Inserisci Password" name="pass" required>
    <br>
    <br>
    <input type="submit" value="Accedi">
  </div>
  <hr>
</form>
    <br>
	<form action = "register" align="center">
	<p>Non sei registrato? <input type="submit" value="Registrati"></p>
	
</form>
</body>
</html>