<%@page import="savjetnikApp.SavjetnikService"%>
<%@page import="savjetnikApp.PorukaBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="savjetnik" class="savjetnikApp.SavjetnikBean" scope="session"></jsp:useBean>
<jsp:useBean id="posiljalac" class="savjetnikApp.SavjetnikBean" scope="request"></jsp:useBean>
<jsp:useBean id="openedMessage" class="savjetnikApp.PorukaBean" scope="request"></jsp:useBean>

   
    <%
    	if (!savjetnik.isLoggedIn()){
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
		openedMessage.setTekst(tekst);
		openedMessage.setDatumSlanja(datumSlanja);
		openedMessage.setPosiljalac(posiljalac);
		openedMessage.getPosiljalac().setUserName(username);
		openedMessage.getPosiljalac().setEmail(email);
		if (procitana.equals("false")){
			openedMessage.setProcitana(true);
			SavjetnikService.updateMessage(savjetnik, openedMessage);
		}
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
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
		
		<form method="post" action="savjetnik.jsp">
			<div style="border: 1px solid black; border-radius: 2%">
				<div style="height: 150px; overflow: auto; font-size: 20px; text-align: justify; padding: 5px;">
					<p> <%= openedMessage.getTekst() %> </p>
				</div>
				<div style="display: flex; width:100%; align-items: center; justify-content: space-between; 
							background-color: lightslategray; font-size: 15px; border-radius: 5%">
					<label style="border: 0;">Poslao: <%= openedMessage.getPosiljalac().getUserName() %> </label>
      				<label style="border: 0;">Datum slanja: <%= openedMessage.getDatumSlanja() %> </label>
				</div>
			</div>
				
			<div>
				<button type="button" onclick="sendEmail('<%= openedMessage.getPosiljalac().getEmail() %>')" >Odgovori</button>
    			<input type="submit" value="Zatvori">
			</div>
		</form>		

	</body>
</html>