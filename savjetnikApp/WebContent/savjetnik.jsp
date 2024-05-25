<%@page import="savjetnikApp.SavjetnikService"%>
<%@page import="savjetnikApp.PorukaBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="savjetnikApp.SavjetnikBean"%>
<jsp:useBean id="savjetnik" class="savjetnikApp.SavjetnikBean" scope="session"></jsp:useBean>

<%
	if (!savjetnik.isLoggedIn()){
		response.sendRedirect("login.jsp");
		return;
	}
%>

    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Savjetnik</title>
		<link rel="stylesheet" href="style.css">
		<script>
			function posaljiPoruku(porukaid, tekst, datumSlanja, username, email, procitana){
				window.location.href = "message.jsp?id=" + porukaid +
										"&tekst=" + tekst +
										"&datumSlanja=" + datumSlanja +
										"&username=" + username +
										"&email=" + email +
										"&procitana=" + procitana;
			}
			
			function filterTable() {
				  var input, filter, table, tr, td, i, txtValue;
				  input = document.getElementById("filterInput");
				  filter = input.value.toUpperCase();
				  table = document.getElementById("dataTable");
				  tr = table.getElementsByTagName("tr");

				  for (i = 0; i < tr.length; i++) {
				    td = tr[i].getElementsByTagName("td")[2]; // Assuming filtering on the third column
				    if (td) {
				      txtValue = td.textContent || td.innerText;
				      if (txtValue.toUpperCase().indexOf(filter) > -1) {
				        tr[i].style.display = "";
				      } else {
				        tr[i].style.display = "none";
				      }
				    }
				  }
				}
		</script>
	</head>
	
	<body>
		<%@include file="header.jsp" %>
		
		<h2>Primljene poruke</h2>
		<input type="text" id="filterInput" onkeyup="filterTable()" placeholder="Pretrazi poruke.." 
				style="margin-bottom: 5px; width:200px;">
		
		<table id="dataTable" style="border-collapse:collapse;  text-align: center; min-width: 60%" border="1">
			<thead> 
				<tr>
					<th>Posiljalac</th>
					<th>Vrijeme slanja</th>
					<th>Tekst poruke</th>
					<th>Procitana</th>
				</tr>
			</thead>
			<tbody>
			<%
				for (PorukaBean message : savjetnik.getPrimljenePoruke()) {
			%> 
			<tr onclick="posaljiPoruku('<%= message.getId() %>', '<%= message.getTekst() %>', 
											'<%= message.getDatumSlanja() %>', '<%= message.getPosiljalac().getUserName() %>', 
											'<%= message.getPosiljalac().getEmail() %>', '<%= message.isProcitana() %>')">
				<td><%= message.getPosiljalac().getUserName() %></td>
				<td><%= message.getDatumSlanja() %></td>
				<td><%= message.getTekst() %></td>
				<td><%= message.isProcitana() %></td>
			</tr>
			<% } %>
			</tbody>
		</table>
	
	<% if (session.getAttribute("alert") != null && session.getAttribute("alert").equals("true")) { %>
    		<script type="text/javascript">
        		alert("Uspjesno ste poslali mejl!");
    		</script>
	<% session.setAttribute("alert", "false"); } %>
	
	</body>
</html>