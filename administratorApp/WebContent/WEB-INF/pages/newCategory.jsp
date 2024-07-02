<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="dto.Atribut"%>
<jsp:useBean id="attributesBean" class="beans.AttributesBeans" scope="session"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Nova kategorija</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    		<h1>fitnessApp</h1>
    			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=logout">Odjava</a> 
    			</label>
			</header>
			
			<div style="display: flex; flex-direction:column; align-items: center; width: 60%">
				<div style="display: flex; align-items: flex-start; ">
					<form method="post" action="?action=newCategory">
				<h3>Kreiraj novu kategoriju</h3>
				<div>
					<label>Naziv kategorije: </label>
    				<input type="text" name="nazivKategorije" autofocus="autofocus" required>
				</div>
				<div>
					<label>Atribut kategorije: </label>
					<select name="atributi" required>
						<%
							for (Atribut atribut : attributesBean.getAtributes()) {
						%> 
							<option value="<%=atribut.getId() %>"> <%=atribut.getAttributeName() %> </option>
						<% } %>
					</select>
				</div>
				<div>
					<input type="submit" value="Kreiraj">
					<label style="text-align: center; width: 70px; background-color: lightgreen;">
    					<a href="?action=administrator">Zatvori</a> 
    				</label>
				</div>
				</form>
		
				<form method="post" action="?action=newCategory">
					<h3>Kreiraj novi atribut</h3>
					<div>
						<label>Naziv atributa: </label>
    					<input type="text" name="nazivAtributa" autofocus="autofocus" required>
					</div>
					<div>
						<label style="visibility: hidden;">Nesto </label>
    					<input type="text" style="visibility: hidden;">
					</div>
				
					<div>
						<input type="submit" value="Kreiraj">
						<label style="text-align: center; width: 70px; background-color: lightgreen;">
    						<a href="?action=administrator">Zatvori</a> 
    					</label>
					</div>
				</form>	
				</div>
							<h3><%=session.getAttribute("notification") %></h3>
			</div>
						
</body>
</html>