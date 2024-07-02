<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Prijava</title>
		<link rel="stylesheet" href="style.css">
	</head>
	
	<body>
		<header>
    		<h1>fitnessApp</h1>
    		<label style="text-align: center; width: 100px; background-color: lightgreen;">
    			<a href="?action=registration">Registracija</a> 
    		</label>
		</header>
	
		<form method="post" action="?action=login">
				<h3>Prijava</h3>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" autofocus="autofocus" required>
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="password" name="password" required>
				</div>
    			<input type="submit" name="submit" value="Uloguj se">
    			
    			<h3><%=session.getAttribute("notification") %></h3>
		</form>

	</body>
</html>