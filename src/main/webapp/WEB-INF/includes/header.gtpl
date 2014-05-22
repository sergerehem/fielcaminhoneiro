<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Clube Fiel Caminhoneiro - Normando Transportes">
    <meta name="author" content="Serge Rehem">

    <title>Clube Fiel Caminhoneiro - Normando Transportes</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet">

    <!-- Add custom CSS here -->
    <link href="/css/sb-admin.css" rel="stylesheet">
    <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/custom.css" rel="stylesheet">

    <!-- Chosen Plugin CSS -->
    <link href="/css/chosen.min.css" rel="stylesheet" type="text/css">
    <link href="/css/chosen-bootstrap.css" rel="stylesheet" type="text/css">

    <!-- Page Specific CSS 
    <link rel="stylesheet" href="/css/morris-0.4.3.min.css">
		-->

    <!-- Footable -->        
    <link href="/footable/css/footable.core.min.css" rel="stylesheet">
    <link href="/footable/css/footable.standalone.min.css" rel="stylesheet">

<!--    <script src="/js/bootstrap-datepicker.js" type="text/javascript"></script> -->

    <link rel="stylesheet" href="/css/ladda-themeless.min.css">

    <script src="/js/jquery-1.10.2.js"></script>
  </head>

<%
def camelCase(username) {
    def (name, domain) = username.tokenize('@')
    name = name.replaceAll("-","_")
    name = name.replaceAll("\\.","_")
    name = name.replaceAll(/_\w/){ " " + it[1].toUpperCase() }
    name.capitalize()
}

String underscoreToCamelCase(String underscore){
if(!underscore || underscore.isAllWhitespace()){
return ''
}
return underscore.replaceAll(/_\w/){ it[1].toUpperCase() }
}
%>
  <body>

    <div class="container-full" id="wrapper">

			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					  <span class="sr-only">Toggle navigation</span>
					  <span class="icon-bar"></span>
					  <span class="icon-bar"></span>
					  <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="/">Clube Fiel Caminhoneiro</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<%if (user != null) {%>
						<li><a href="/ranking"><i class="fa fa-trophy"></i> Ranking</a></li>
						<li><a href="/motoristas"><i class="fa fa-user"></i> Motoristas</a></li>
						<li><a href="/sms"><i class="fa fa-comments"></i> SMS</a></li>						
						<li><a href="/grupos"><i class="fa fa-users"></i> Grupos</a></li>
						<li><a href="/pontos"><i class="fa fa-certificate"></i> Pontos</a></li>
						<li><a href="/dashboard"><i class="fa fa-dashboard"></i> Painel</a></li>
		        <li class="dropdown user-dropdown">
		          <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${camelCase(user?.nickname)}<b class="caret"></b></a>
		          <ul class="dropdown-menu">
		            <li><a href="${users.createLogoutURL('/')}"><i class="fa fa-sign-out"></i> Logout</a></li>
		          </ul>
		        </li>
						<%} else {%>
						<li><a href="/login?continueTo=http://${request.serverName}:${request.serverPort}"><i class="fa fa-sign-in"></i> Login </a></li>
						<%}%>
					</ul>
				</div><!-- /.navbar-collapse -->
			</nav><!-- /.nav -->
		  
			<div id="page-wrapper">
