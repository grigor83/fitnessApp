

<%@page import="savjetnikApp.SavjetnikBean"%>

<script type="text/javascript">
	function registration() {
    	window.location.href = 'registration.jsp';
	}

   function logout() {
        window.location.href = 'logout.jsp';
   }
</script>



<header>
    <h1>fitnessApp</h1>
    
    <%
    	if (!((SavjetnikBean) session.getAttribute("savjetnik")).isLoggedIn()) { %>
    		<button onClick="registration()">Registracija</button>
    <%  } 
        else { %>
        	<button onClick="logout()">Odjava</button>
    <%  } %>
    
</header>

