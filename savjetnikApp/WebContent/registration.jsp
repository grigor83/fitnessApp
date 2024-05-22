<%@page import="savjetnikApp.SavjetnikService"%>
<%@page import="savjetnikApp.SavjetnikBean"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="newSavjetnik" class="savjetnikApp.SavjetnikBean" scope="request"></jsp:useBean>
<jsp:setProperty name="newSavjetnik" property="name" param="name"/>
<jsp:setProperty name="newSavjetnik" property="userName" param="username"/>
<jsp:setProperty name="newSavjetnik" property="password" param="password"/>
<jsp:setProperty name="newSavjetnik" property="email" param="email"/>

    
    <%
    if (request.getParameter("submit") != null){
    	if (SavjetnikService.isUsernameDuplicate(newSavjetnik.getUserName())){
    		session.setAttribute("notifikacija", "Korisnicko ime je vec zauzeto!");
    	}
    	else if (SavjetnikService.createSavjetnik(newSavjetnik)){
    		SavjetnikService.loadUsers();
    		session.setAttribute("notifikacija", "Nalog je kreiran! Sacekajte na odobrenje administratora!");
    	}
    }
    else
    	session.setAttribute("notifikacija", "");
     %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration</title>
		<link rel="stylesheet" href="style.css">
		<script type="text/javascript">
			function login() {
    			window.location.href = 'login.jsp';
			}
		</script>
	</head>


	<body>
		<header>
    		<h1>fitnessApp</h1>
    		<button onClick="login()">Prijava</button>
		</header>
		
		<form method="post" action="registration.jsp">
				<h3>Registracija</h3>
				<div>
					<label>Ime: </label>
    				<input type="text" name="name" autofocus="autofocus" required>
				</div>
				<div>
					<label>Korisnicko ime: </label>
    				<input type="text" name="username" required>
				</div>
				<div>
					<label>Lozinka: </label>
    				<input type="password" name="password" required>
				</div>
				<div>
					<label>Email: </label>
    				<input type="email" name="email" required>
				</div>
    			<input type="submit" name="submit" value="Registruj se">
    			
    			<h3><%=session.getAttribute("notifikacija").toString() %></h3>
		</form>

	</body>
</html>