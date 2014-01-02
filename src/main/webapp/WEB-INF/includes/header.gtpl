<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

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

  </head>

  <body>

    <div id="wrapper">

      <!-- Sidebar -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">Clube Fiel Caminhoneiro</a>
        </div>

          <ul class="nav navbar-nav navbar-right navbar-user">
						<%if (user != null) {%>
						<!--<li><a href="/ranking"><i class="fa fa-trophy"></i> Ranking</a></li>-->
						<li><a href="/motoristas"><i class="fa fa-user"></i> Motoristas</a></li>
						<li><a href="/grupos"><i class="fa fa-users"></i> Grupos</a></li>
						<li><a href="/pontos"><i class="fa fa-certificate"></i> Pontos</a></li>
						<%}%>
            <li class="dropdown user-dropdown">
							<%if (user != null) {%>
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${user?.nickname}<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="${users.createLogoutURL('/')}"><i class="fa fa-power-off"></i> Log Out</a></li>
              </ul>
							<%} else {%>
							<a href="/login?continueTo=/">login</a>
						<%}%>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </nav>

      <div id="page-wrapper">
