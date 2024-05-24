<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="savjetnikApp.SavjetnikService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload" %>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory" %>


<jsp:useBean id="savjetnik" class="savjetnikApp.SavjetnikBean" scope="session"></jsp:useBean>

		<%
		String emailAddress = request.getParameter("emailAddress");	
				 	
				 	if(ServletFileUpload.isMultipartContent(request)) {
				 		ArrayList<String> params = SavjetnikService.getParametersFromRequest(request);
				 		new Thread(new Runnable(){
				 			public void run(){
				 		SavjetnikService.sendEmailWithAttachements(params);
				 			}
				 		}).start();
				 		session.setAttribute("alert", "true");
				 		response.sendRedirect("savjetnik.jsp");	
				 	}
		%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Send email</title>
		<link rel="stylesheet" href="style.css">
		<script>
			function redirectToSavjetnik() {
				window.location.href = "savjetnik.jsp";
			}
		</script>
	</head>

	<body>
		<%@include file="header.jsp" %>
		
		<form method="post" action="sendEmail.jsp"  enctype="multipart/form-data">
				<h3>Posalji mejl</h3>
				<div>
					<label>Primalac: </label>
    				<input type="text" name="primalac" required style="width: 200px;" value="<%= emailAddress %>" >
				</div>
				<div>
					<label>Posiljalac: </label>
    				<input type="text" name="posiljalac" required style="width: 200px;" value="<%= savjetnik.getEmail() %>">
				</div>
				<div>
					<label>Naslov*: </label>
    				<input type="text" name="naslov" autofocus="autofocus" required style="width: 200px;">
				</div>
				<div style="display: flex; align-items: center; align-self: center;">
					<label>Poruka*: </label>
    				<textarea name=poruka rows="5" required style="width: 190px; padding: 5px;"></textarea>
				</div>
				<div>
					<label>Prikaci fajl <input type="file" name="fajl" accept="image/*, .txt, .pdf" hidden="true"></label>
				</div>
				
				<div>
				    <input type="submit" name="send" value="Posalji mejl">
				    <button type="button" onclick="redirectToSavjetnik()">Odustani</button>
				</div>
				
		</form>
	</body>
</html>