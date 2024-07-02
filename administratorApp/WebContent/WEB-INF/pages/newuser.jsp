<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Novi korisnik</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
		<header>
    			<h1>fitnessApp</h1>
    			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=logout">Odjava</a> 
    			</label>
		</header>
		
		<form method="post" action="?action=newUser">
				<h3>Novi korisnik</h3>
				<div>
					<label>Ime: </label>
    				<input type="text" name="ime" autofocus="autofocus" required >
				</div>
				<div>
					<label>Grad: </label>
    				<input type="text" name="grad" required >
				</div>
				<div>
					<label>Email: </label>
    				<input type="email" name="email" required >
				</div>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" required >
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="text" name="password" required >
				</div>
				<div>
					<label>Savjetnik: </label>
    				<select name="savjetnik" required>
    					<option value="null"></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				<div>
					<label>Verifikovan: </label>
    				<select name="verifikovan" required>
    					<option value="null"></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				<div>
					<label>Broj kartice: </label>
    				<input type="text" name="brojkartice" required >
				</div>
				
				<div>
					<input type="submit" value="Kreiraj">
					<label style="text-align: center; width: 70px; background-color: lightgreen;">
    						<a href="?action=administrator">Zatvori</a> 
    				</label>
				</div>
							<h3><%=session.getAttribute("notification") %></h3>
		</form>

</body>
</html>