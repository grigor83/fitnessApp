<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="selectedUser" class="dto.User" scope="session"></jsp:useBean>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
    				value="<%= selectedUser.getFirstName() %>" >
				</div>
				<div>
					<label>Grad: </label>
    				<input type="text" name="grad" required
    				value="<%= selectedUser.getCity() %>" >
				</div>
				<div>
					<label>Email: </label>
    				<input type="email" name="email" required
    				value="<%= selectedUser.getEmail() %>" >
				</div>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" required
    				value="<%= selectedUser.getUsername() %>" >
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="text" name="password" required
    				value="<%= selectedUser.getPassword() %>" >
				</div>
				<div>
					<label>Savjetnik: </label>
					<select name="savjetnik" required>
    					<option value="<%= selectedUser.isCouncelor() %>" selected><%= selectedUser.isCouncelor() %></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				<div>
					<label>Verifikovan: </label>
    				<select name="verifikovan" required>
    					<option value="<%= selectedUser.isVerified() %>" selected><%= selectedUser.isVerified() %></option>
    					<option value="false">false</option>
    					<option value="true">true</option>
    				</select>
				</div>
				
				<input type="submit" value="Izmijeni">
		</form>

</body>
</html>