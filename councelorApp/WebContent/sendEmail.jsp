<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="councelorApp.CouncelorService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload" %>
<%@page import="org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory" %>


<jsp:useBean id="councelor" class="councelorApp.CouncelorBean" scope="session"></jsp:useBean>

		<%
		String emailAddress = request.getParameter("emailAddress");	
				 	
				 	if(ServletFileUpload.isMultipartContent(request)) {
				 		ArrayList<String> params = CouncelorService.getParametersFromRequest(request);
				 		new Thread(new Runnable(){
				 			public void run(){
				 		CouncelorService.sendEmailWithAttachements(params);
				 			}
				 		}).start();
				 		session.setAttribute("alert", "true");
				 		response.sendRedirect("councelor.jsp");	
				 	}
		%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Send email</title>
		<link rel="stylesheet" href="style.css">
		<script>
			function redirectToSavjetnik() {
				window.location.href = "councelor.jsp";
			}
		</script>
	</head>

	<body>
		<%@include file="header.jsp" %>
		
		<form method="post" action="sendEmail.jsp"  enctype="multipart/form-data">
				<h3>Pošalji mejl</h3>
				<div>
					<label>Primalac: </label>
    				<input type="text" name="primalac" required style="width: 200px;" value="<%= emailAddress %>" >
				</div>
				<div>
					<label>Pošiljalac: </label>
    				<input type="text" name="posiljalac" required style="width: 200px;" value="<%= councelor.getEmail() %>">
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
					<label>Prikači fajl <input type="file" name="fajl" accept="image/*, .txt, .pdf" hidden="true"></label>
				</div>
				
				<div>
				    <input type="submit" name="send" value="Pošalji mejl">
				    <button type="button" onclick="redirectToSavjetnik()">Odustani</button>
				</div>
				
		</form>
	</body>
</html>