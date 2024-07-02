<%@page import="councelorApp.CouncelorService"%>
<%@page import="councelorApp.CouncelorBean"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="newSavjetnik" class="councelorApp.CouncelorBean" scope="request"></jsp:useBean>
<jsp:setProperty name="newSavjetnik" property="name" param="name"/>
<jsp:setProperty name="newSavjetnik" property="userName" param="username"/>
<jsp:setProperty name="newSavjetnik" property="password" param="password"/>
<jsp:setProperty name="newSavjetnik" property="email" param="email"/>

    
    <%
        if (request.getParameter("submit") != null){
                    	if (CouncelorService.isUsernameDuplicate(newSavjetnik.getUserName())){
                    		session.setAttribute("notifikacija", "Korisničko ime je vec zauzeto!");
                    	}
                    	else if (CouncelorService.insertSavjetnik(newSavjetnik)){
                    		CouncelorService.loadUsers();
                    		session.setAttribute("notifikacija", "Nalog je kreiran! Sačekajte na odobrenje administratora!");
                    	}
                    }
                    else
                    	session.setAttribute("notifikacija", "");
        %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
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
					<label>Korisničko ime: </label>
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