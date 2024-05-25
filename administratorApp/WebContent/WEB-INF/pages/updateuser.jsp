<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="selectedUser" class="dto.User" scope="session"></jsp:useBean>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Promijeni podatke o korisniku</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
		<header>
    			<h1>fitnessApp</h1>
    			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=logout">Odjava</a> 
    			</label>
		</header>
		
		<form method="post" action="?action=updateUser">
				<h3>Izmijeni podatke o korisniku</h3>
				<div>
					<label>Ime: </label>
    				<input type="text" name="ime" autofocus="autofocus" required
    				value="<%= selectedUser.getIme() %>" >
				</div>
				<div>
					<label>Grad: </label>
    				<input type="text" name="grad" required
    				value="<%= selectedUser.getGrad() %>" >
				</div>
				<div>
					<label>Email: </label>
    				<input type="email" name="email" required
    				value="<%= selectedUser.getEmail() %>" >
				</div>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" required
    				value="<%= selectedUser.getKorisnickoIme() %>" >
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="text" name="password" required
    				value="<%= selectedUser.getLozinka() %>" >
				</div>
				<div>
					<label>Savjetnik: </label>
					<select name="savjetnik" required>
    					<option value="<%= selectedUser.isSavjetnik() %>" selected><%= selectedUser.isSavjetnik() %></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				<div>
					<label>Verifikovan: </label>
    				<select name="verifikovan" required>
    					<option value="<%= selectedUser.isVerifikovan() %>" selected><%= selectedUser.isVerifikovan() %></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				
				<input type="submit" value="Izmijeni">
		</form>

</body>
</html>