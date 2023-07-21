<%
	if(session.getAttribute("username") == null){
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<%@ include file = "CDN.html" %>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>
<!-- necessaria valutazione dinamica quindi uso l'action -->
<jsp:include page="nav.jsp"/>
	<div class = "container">
		<header class = "page-header">
			<h3>Inserisci i dati per la registrazione</h3>
		</header>
		<from action="/<%=application.getServletContextName() %>/registra" method="post" class="from-horizontal">
			<!-- ----- Nome ----- -->
			<div class="form-group">
				<label class="col-md-1 control-label">Nome</label>
				<div class="col-md-4 inputGroupContainer">
					<div class="input-group">
						<span class="input-group-addon">
							<i class="glyphicon glyphicon-user"></i>
						</span>
						<input type="text" name="nome" placeholder="Nome..." class="fomr-control">
					</div>
				</div>
			</div>
			<div class="col-md-7 error" id="infoNome"></div>
		</from>
	</div>
</body>
<footer> <%@include file = "footer.html" %> </footer>
</html>

<%
	} else {
		response.sendRedirect("acquisti.jsp");
	}
%>