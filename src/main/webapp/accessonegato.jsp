<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- includo una sola volta con la direttiva @include -->
<%@ include file = "CDN.html" %>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/style.css">
<title>Accesso Negato</title>
</head>
<body>
<!-- necessaria valutazione dinamica quindi uso l'action -->
<jsp:include page="nav.jsp"/>
	<div class = "container">
		<header class = "page-header">
			<h3>Non puoi accedere a questa pagina</h3>
		</header>
		
		<div class = "panel panel-danger">
			<div class = "panel-heading">
				<h3>Risorsa non disponibile</h3>
			</div>	
			<div class = "panel-body">
				<p>effettuare la registrazione </p>
				<p>a<a href="registra.jsp">Sign-up</a></p>
				<p>Oppure se registrati effettuare l'accesso</p>
				<p><a href="login.jsp">Login</a>
			</div>
		</div>
	</div>
</body>
<!-- il footer è un tag semantico in cui inserire i tag di navigazione -->
<footer> <%@include file = "footer.html" %> </footer>

</html>