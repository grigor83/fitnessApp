<%@page import="councelorApp.CouncelorService"%>
<%@page import="councelorApp.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="councelor" class="councelorApp.CouncelorBean" scope="session"></jsp:useBean>
<jsp:useBean id="sender" class="councelorApp.CouncelorBean" scope="request"></jsp:useBean>
<jsp:useBean id="openedMessage" class="councelorApp.MessageBean" scope="request"></jsp:useBean>

   
    <%
       if (!councelor.isLoggedIn()){
              	response.sendRedirect("login.jsp");
              	return;
        }
                  
                    int id = Integer.parseInt(request.getParameter("id"));
                  	String tekst = request.getParameter("tekst");
                  	String datumSlanja = request.getParameter("datumSlanja");
                  	String username = request.getParameter("username");
                  	String email = request.getParameter("email");
                  	String procitana = request.getParameter("procitana");
                  	
                  	openedMessage.setId(id);
              		openedMessage.setContent(tekst);
              		openedMessage.setSentDate(datumSlanja);
              		openedMessage.setSender(sender);
              		openedMessage.getSender().setUserName(username);
              		openedMessage.getSender().setEmail(email);
              		if (procitana.equals("false")){
              			openedMessage.setSeen(true);
              			CouncelorService.updateMessage(councelor, openedMessage);
              		}
       %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Poruka</title>
		<link rel="stylesheet" href="style.css">
		<script>
			function sendEmail(emailAddress) {
				window.location.href = "sendEmail.jsp?emailAddress=" + emailAddress;
			}
		</script>
	</head>
	
	<body>
		<%@include file="header.jsp" %>
		
		<form method="post" action="councelor.jsp">
			<div style="border: 1px solid black; border-radius: 2%">
				<div style="height: 150px; overflow: auto; font-size: 20px; text-align: justify; padding: 5px;">
					<p> <%= openedMessage.getContent() %> </p>
				</div>
				<div style="display: flex; width:100%; align-items: center; justify-content: space-between; 
							background-color: lightslategray; font-size: 15px; border-radius: 5%">
					<label style="border: 0;">Poslao: <%= openedMessage.getSender().getUserName() %> </label>
      				<label style="border: 0;">Datum slanja: <%= openedMessage.getSentDate() %> </label>
				</div>
			</div>
				
			<div>
				<button type="button" onclick="sendEmail('<%= openedMessage.getSender().getEmail() %>')" >Odgovori</button>
    			<input type="submit" value="Zatvori">
			</div>
		</form>		

	</body>
</html>