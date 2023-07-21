<jsp:useBean id ="carrello" class="com.luca.ordini.businesscomponent.utilities.Carrello" scope="session"/>
<!-- dati nel carrello visibili solo in sessione -->
<!-- navbar-inverse è scuro, poco ci interessa considrando che il colore glielo si è settato nello style.css -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <!-- bottone con evento d'apertura e chiusura associato -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <!-- le icon bar sono le lineette nel bottone -->
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <!-- getServletContextName() per recuperare il nome del progetto -->
      <a class="navbar-brand" href="/<%=application.getServletContextName()%>/home.jsp">Negozio On-line</a>
    </div>
    <div class="collapse navbar-collapse" id="menu">
    <%
    	String username = (String) session.getAttribute("username");
    	if(username == null){
    %>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/<%=application.getServletContextName()%>/registra.jsp"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
        <li><a href="/<%=application.getServletContextName()%>/login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
      
     <%
    	} else {
    %>
	  <ul class="nav navbar-nav">
        <li><a href="/<%=application.getServletContextName()%>/acquisti.jsp">Scelta articoli</a></li>
        <li><a href="/<%=application.getServletContextName()%>/carrello.jsp">Riepilogo carrello</a></li>
      </ul>
      
      <ul class="nav navbar-nav">
        <li><a href="/<%=application.getServletContextName()%>/carrello.jsp"><span class="glyphicon glyphicon-shopping-cart"></span>Scelta articoli
        <!-- span = blocco in linea utile a fromattare -->
        <span class="badge"><%= carrello.getArticoli() %></span>
        </a></li>
        <li><a href="#"><span class="glyphicon glyphicon-user"></span><%= username %></a></li>
        <li><a href="/<%=application.getServletContextName()%>/logout.jsp">
        <span class="glyphicon glyphicon-off"></span>Logout
        </a></li>
      </ul>
    <%
    	}
    %>
    </div>
  </div>
</nav>