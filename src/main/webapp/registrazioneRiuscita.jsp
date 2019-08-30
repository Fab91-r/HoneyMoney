<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione riuscita</title>
</head>
<body>
<%String user = (String)request.getAttribute("user"); %>
<h1><%=user%> ti sei registrato con successo!</h1>
<form action="intro.jsp" >
 <input type="submit" value="Torna alla Home">
</form>
</body>
</html>