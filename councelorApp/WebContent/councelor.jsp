<%@page import="councelorApp.CouncelorService"%>
<%@page import="councelorApp.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="councelorApp.CouncelorBean"%>
<jsp:useBean id="councelor" class="councelorApp.CouncelorBean" scope="session"></jsp:useBean>

<%
if (!councelor.isLoggedIn()){
		response.sendRedirect("login.jsp");
		return;
	}
%>

    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
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
		<input type="text" id="filterInput" onkeyup="filterTable()" placeholder="Pretraži poruke.." 
				style="margin-bottom: 5px; width:200px;">
		
		<table id="dataTable" style="border-collapse:collapse;  text-align: center; min-width: 60%" border="1">
			<thead> 
				<tr>
					<th>Pošiljalac</th>
					<th>Vrijeme slanja</th>
					<th>Tekst poruke</th>
					<th>Pročitana</th>
				</tr>
			</thead>
			<tbody>
			<%
			for (MessageBean message : councelor.getRecievedMessages()) {
			%> 
			<tr onclick="posaljiPoruku('<%= message.getId() %>', '<%= message.getContent()%>', 
											'<%= message.getSentDate() %>', '<%= message.getSender().getUserName() %>', 
											'<%= message.getSender().getEmail() %>', '<%= message.isSeen() %>')">
				<td><%= message.getSender().getUserName() %></td>
				<td><%= message.getSentDate() %></td>
				<td><%= message.getContent() %></td>
				<td><%= message.isSeen() %></td>
			</tr>
			<% } %>
			</tbody>
		</table>
	
	<% if (session.getAttribute("alert") != null && session.getAttribute("alert").equals("true")) { %>
    		<script type="text/javascript">
        		alert("Uspješno ste poslali mejl!");
    		</script>
	<% session.setAttribute("alert", "false"); } %>
	
	</body>
</html>