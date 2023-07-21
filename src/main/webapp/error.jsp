<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.PrintStream"%>
<%@page import="com.luca.ordini.architecture.dao.DAOException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<!-- includo una sola volta con la direttiva @include -->
<%@ include file = "CDN.html" %>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/<%=application.getServletContextName()%>/css/style.css">
<title>Error page</title>
</head>
<body>
<!-- necessaria valutazione dinamica quindi uso l'action -->
<jsp:include page="nav.jsp"/>
	<div class = "container">
		<header class = "page-header">
			<h3>Pagine di errore</h3>
		</header>
		
		<%
			if(exception instanceof ClassNotFoundException){
		%>
		<div class = "panel panel-danger">
			<div class = "panel-heading">
				<!-- getName() così conosco anche il nome del pacchetto -->
				<h5>Tipo eccezione: <%= exception.getClass().getName() %></h5>
			</div>	
			<div class = "panel-body">
				<p>Message: <%= exception.getMessage() %></p>
				<!-- window.history.back() per tornare indietro -->
				<p><button onclick="window.history.back()" class="btn btn-default"></button></p>
			</div>
		</div>
		<%
			}else if(exception instanceof DAOException){
		%>
		<div class = "panel panel-danger">
			<div class = "panel-heading">
				<h5>Tipo eccezione: <%= exception.getClass().getName() %></h5>
			</div>	
			<div class = "panel-body">
				<p>Message: <%= exception.getMessage() %></p>
				<p><button onclick="window.history.back()" class="btn btn-default"></button></p>
			</div>
		</div>
		<%
			} else {
		%>
		<div class = "panel panel-danger">
			<div class = "panel-heading">
				<h5>Tipo eccezione: <%= exception.getClass().getName() %></h5>
			</div>	
			<div class = "panel-body">
				<p>Message: <%= exception.getMessage() %></p>
				<!-- scrive il motivo dell'exc sulla pagina con tutto lo StackTrace -->
				<p>StackTrace: <%= exception.printStackTrace(new PrintWriter(out)) %>
				<p><button onclick="window.history.back()" class="btn btn-default"></button></p>
			</div>
		</div>
		<%
			}
		%>
	</div>
</body>
<!-- il footer è un tag semantico in cui inserire i tag di navigazione -->
<footer> <%@include file = "footer.html" %> </footer>

</html>