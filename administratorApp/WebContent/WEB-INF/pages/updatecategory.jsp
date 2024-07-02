<%@page import="dto.Atribut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="selectedCategory" class="dto.Category" scope="session"></jsp:useBean>
<jsp:useBean id="attributesBean" class="beans.AttributesBeans" scope="session"></jsp:useBean>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Izmijeni kategoriju</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
		<header>
    			<h1>fitnessApp</h1>
    			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=logout">Odjava</a> 
    			</label>
		</header>
		
		<form method="post" action="?action=updateCategory">
				<h3>Izmijeni kategoriju</h3>
				<div>
					<label>Naziv kategorije: </label>
    				<input type="text" name="nazivKategorije" autofocus="autofocus" required
    				value="<%= selectedCategory.getCategoryName() %>" >
				</div>
				<div>
					<label>Atribut kategorije: </label>
					<select name="atributi" required>
						<option value="<%=selectedCategory.getAttributeId() %>" selected><%=selectedCategory.getAttributeName() %></option>
						<%
							for (Atribut atribut : attributesBean.getAtributes()) {
						%> 
							<option value="<%=atribut.getId() %>"> <%=atribut.getAttributeName() %> </option>
						<% } %>
					</select>
				</div>
				<input type="submit" value="Izmijeni">
		</form>

</body>
</html>