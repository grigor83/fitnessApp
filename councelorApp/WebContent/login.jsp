<%@page import="councelorApp.CouncelorService"%>
<%@page import="councelorApp.CouncelorBean" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="councelor" class="councelorApp.CouncelorBean" scope="session"></jsp:useBean>
<jsp:setProperty name="councelor" property="userName" param="username"/>
<jsp:setProperty name="councelor" property="password" param="password"/>

    
    <%
                Object pokrenuta = application.getAttribute("prvoPokretanje");
                    	
                    	if (pokrenuta == null){
                        	CouncelorService.loadUsers();
                        	application.setAttribute("prvoPokretanje", "true");
                     	}
                    	
                    	if (request.getParameter("submit") != null){
                            CouncelorBean s = CouncelorService.login(councelor.getUserName(), councelor.getPassword());
                            if (s != null){
                            	councelor.setName(s.getName());
                            	councelor.setEmail(s.getEmail());
                            	councelor.setRecievedMessages(s.getRecievedMessages());
                            	councelor.setLoggedIn(true);
                             	session.setAttribute("notifikacija", "");
                             	response.sendRedirect("councelor.jsp");
                            }
                            else {
                             	session.setAttribute("notifikacija", "Unijeli ste neispravno korisničko ime i lozinku!");
                            }
                         }
                         else 
                         	session.setAttribute("notifikacija", "");
        %>
    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<link rel="stylesheet" href="style.css">
	</head>

	<body>
			<%@include file="header.jsp" %>
			
			<form method="post" action="login.jsp">
				<h3>Prijava</h3>
				<div>
					<label>Korisničko ime: </label>
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