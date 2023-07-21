<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- includo una sola volta con la direttiva @include -->
<%@ include file = "CDN.html" %>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/<%=application.getServletContextName()%>/css/style.css">
<title>Error 403</title>
</head>
<body>
<!-- necessaria valutazione dinamica quindi uso l'action -->
<jsp:include page="nav.jsp"/>
	<div class = "container">
		<header class = "page-header">
			<h3>Autorizzazione negata</h3>
		</header>
		
		<div class = "panel panel-danger">
			<div class = "panel-heading">
				<h3>Impossibile caricare la risorsa richiesta</h3>
			</div>	
			<div class = "panel-body">
				<p>Non hai l'autorizzazione per accedere</p>
				<p><button onclick="window.history.back()" class="btn btn-default"></button></p>
			</div>
		</div>
	</div>
</body>
<!-- il footer è un tag semantico in cui inserire i tag di navigazione -->
<footer> <%@include file = "footer.html" %> </footer>

</html>