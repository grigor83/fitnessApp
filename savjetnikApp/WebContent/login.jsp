<%@page import="savjetnikApp.SavjetnikService"%>
<%@page import="savjetnikApp.SavjetnikBean" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="savjetnik" class="savjetnikApp.SavjetnikBean" scope="session"></jsp:useBean>
<jsp:setProperty name="savjetnik" property="userName" param="username"/>
<jsp:setProperty name="savjetnik" property="password" param="password"/>

    
    <%
        Object pokrenuta = application.getAttribute("prvoPokretanje");
                                if (pokrenuta == null){
                                   SavjetnikService.loadUsers();
                                   application.setAttribute("prvoPokretanje", "true");
                                }
                                    
                                if (request.getParameter("submit") != null){
                                   SavjetnikBean s = SavjetnikService.login(savjetnik.getUserName(), savjetnik.getPassword());
                                   if (s != null){
                                	    savjetnik.setName(s.getName());
                                	    savjetnik.setEmail(s.getEmail());
                                	    savjetnik.setPrimljenePoruke(s.getPrimljenePoruke());
                                    	savjetnik.setLoggedIn(true);
                                    	session.setAttribute("notifikacija", "");
                                    	response.sendRedirect("savjetnik.jsp");
                                   }
                                   else {
                                    	session.setAttribute("notifikacija", "Unijeli ste neispravno korisnicko ime i lozinku!");
                                   }
                                }
                                else 
                                	session.setAttribute("notifikacija", "");
        %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Login</title>
		<link rel="stylesheet" href="style.css">
	</head>

	<body>
			<%@include file="header.jsp" %>
			
			<form method="post" action="login.jsp">
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
    			
    			<h3><%=session.getAttribute("notifikacija").toString() %></h3>
			</form>
	</body>
</html>