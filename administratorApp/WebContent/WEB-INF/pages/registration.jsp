<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registracija</title>
		<link rel="stylesheet" href="style.css">
	</head>
	
	<body>
		<header>
    		<h1>fitnessApp</h1>
    		<label style="text-align: center; width: 100px; background-color: lightgreen;">
    			<a href="?action=login">Prijava</a> 
    		</label>
		</header>
		
		<form method="post" action="?action=registration">
				<h3>Registracija</h3>
				<div>
					<label>Ime: </label>
    				<input type="text" name="firstname" autofocus="autofocus" required>
				</div>
				<div>
					<label>Prezime: </label>
    				<input type="text" name="lastname"  required>
				</div>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" required>
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="password" name="password" required>
				</div>
			
    			<input type="submit" name="submit" value="Registruj se">
    			<h3><%=session.getAttribute("notification").toString() %></h3> <br />
		</form>

</body>
</html>