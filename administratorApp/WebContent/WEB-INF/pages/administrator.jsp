<%@page import="beans.LogBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="dto.Category"%>
<jsp:useBean id="categoriesBean" class="beans.CategoriesBean" scope="session"></jsp:useBean>
<%@page import="dto.User"%>
<jsp:useBean id="usersBean" class="beans.UsersBean" scope="session"></jsp:useBean>
<jsp:useBean id="logsBean" class="beans.LogBean" scope="session"></jsp:useBean>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Administrator</title>
	<link rel="stylesheet" href="style.css">
	<script type="text/javascript">
		function openTabContent(event, tabcontentName) {
		  var i, tabcontent, tablinks;
		  // Get all elements with class="tabcontent" and hide them
		  tabcontent = document.getElementsByClassName("tabcontent");
		  for (i = 0; i < tabcontent.length; i++)
		    tabcontent[i].style.display = "none";
		  // Get all elements with class="tablinks" and remove the class "active"
		  tablinks = document.getElementsByClassName("tablinks");
		  for (i = 0; i < tablinks.length; i++) 
		    tablinks[i].className = tablinks[i].className.replace(" active", "");
		  // Show the current tab, and add an "active" class to the button that opened the tab
		  document.getElementById(tabcontentName).style.display = "block";
		  event.currentTarget.className += " active";
		  
		  if (tabcontentName === 'statistics')
			  document.getElementById('reloadLogs').click();
		  else if (tabcontentName === 'users')
				  document.getElementById('reloadUsers').click();
		}
	</script>
</head>
<body>
		<header>
    		<h1>fitnessApp</h1>
    			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=logout">Odjava</a> 
    			</label>
		</header>
		
		<div class="tab">
			<button id="kategorije" class="tablinks" onclick="openTabContent(event, 'categories')">Kategorije</button>
  			<button id="korisnici" class="tablinks" onclick="openTabContent(event, 'users')">Korisnici</button>
  			<button id="statistika" class="tablinks" onclick="openTabContent(event, 'statistics')">Statistika</button>
		</div>
		
		<!-- Tab content -->
		<div id="categories" class="tabcontent">
 			<table border="1">
				<thead> 
					<tr>
					<th>Naziv kategorije</th>
					<th>Atribut kategorije</th>
					<th>Izmijeni kategoriju</th>
					<th>Obriši kategoriju</th>
					</tr>
				</thead>
				<tbody>
				<%
					for (Category category : categoriesBean.getCategories()) {
				%> 
					<tr>
						<td><%= category.getCategoryName() %></td>
						<td><%= category.getAttributeName() %></td>
						<td><label style="text-align: center; width: 80px;"><a href="?action=updateCategory&id=<%= category.getId() %>">Izmijeni</a></label> </td>
            			<td><label style="text-align: center; width: 80px;"><a href="?action=deleteCategory&id=<%= category.getId() %>">Obrisi</a></label> </td>
					</tr>
				<% } %>
				</tbody>
			</table>
			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=newCategory">Kreiraj novu kategoriju</a> 
    		</label>
		</div>

		<div id="users" class="tabcontent">
  			<table border="1">
				<thead> 
					<tr>
					<th>Ime</th>
					<th>Grad</th>
					<th>Korisničko ime</th>
					<th>Lozinka</th>
					<th>Savjetnik</th>
					<th>Verifikovan</th>
					<th>Izmijeni korisnika</th>
					<th>Obriši korisnika</th>
					</tr>
				</thead>
				<tbody>
				<%
					for (User user : usersBean.getUsers()) {
				%> 
					<tr>
						<td><%= user.getFirstName() %></td>
						<td><%= user.getCity() %></td>
						<td><%= user.getUsername() %></td>
						<td><%= user.getCardNumber() %></td>
						<td><%= user.isCouncelor() %></td>
						<td><%= user.isVerified() %></td>
						<td><label style="text-align: center; width: 80px;"><a href="?action=updateUser&id=<%= user.getId() %>">Izmijeni</a></label> </td>
            			<td><label style="text-align: center; width: 80px;"><a href="?action=deleteUser&id=<%= user.getId() %>">Obrisi</a></label> </td>
					</tr>
				<% } %>
				</tbody>
			</table>
			<label style="text-align: center; width: 100px; background-color: lightgreen;">
    				<a href="?action=newUser">Kreiraj novog korisnika</a> 
    		</label>
		</div>

		<div id="statistics" class="tabcontent">
			<table border="1">
				<thead> 
					<tr>
					<th>Poruka</th>
					<th>Datum</th>
					<th>Logger</th>
					</tr>
				</thead>
					<tbody>
				<%
					for (LogBean log : logsBean.getLogs()) {
				%> 
					<tr>
						<td><%= log.getMessage() %></td>
						<td><%= log.getLogDate() %></td>
						<td><%= log.getLogger() %></td>
					</tr>
				<% } %>
					</tbody>
				</table>
		</div>
		
		<script>
		// Get the element with id="defaultOpen" and click on it
			document.getElementById("<%=session.getAttribute("defaultTab") %>").click();
		</script>
		
		<div class="hidden-elements" hidden="true">
			<a id="reloadLogs" href="?action=statistics"></a>
			<a id="reloadUsers" href="?action=users"></a>
		</div>

</body>
</html>