<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function checkPasswordMatch() {
    var password = $("#txtNewPassword").val();
    var confirmPassword = $("#txtConfirmPassword").val();
    var bt = document.getElementById('btSubmit');

    if (password != confirmPassword){
        $("#divCheckPasswordMatch").html("Le password non corrispondono!");
    bt.disabled = true;}
    else if (password == confirmPassword && password.length != 0  && confirmPassword.length != 0){
        $("#divCheckPasswordMatch").html("Password confermata correttamente");
    bt.disabled = false;}
    else {
        $("#divCheckPasswordMatch").html("");
        bt.disabled = true;}  	
} 

$(document).ready(function () {
   $("#txtNewPassword, #txtConfirmPassword").keyup(checkPasswordMatch);
});
</script>
</head>
<body>
<form action="register" method= "POST" align="center">
  <div class="container">
    <h1>Registrazione Account</h1>
    <p>Inserisci i tuoi dati per creare un nuovo account</p>
    <label for="user"><b>Username</b></label>
    <input type="text" placeholder="Inserisci il tuo Username" name="user" required>
	<br>
	<br>
    <label for="pass"><b>Password&nbsp</b></label>
    <input type="password" placeholder="Inserisci la tua Password" name="pass" required id="txtNewPassword">
    <br>
    <br>
    <label for="pass2"><b>Password&nbsp</b></label>
    <input type="password" placeholder="Ripeti la tua Password" name="pass2" required id="txtConfirmPassword" onChange="checkPasswordMatch();">
    <br>
    <br>
    </div>
    <div class="registrationFormAlert" id="divCheckPasswordMatch" style="color:blue;">
</div>
    <br>
    <br>
    <hr>
    <div>
    <button type="submit" class="registerbtn" id="btSubmit">Registrati</button>
  </div>
  
</form>

</body>
</html>

