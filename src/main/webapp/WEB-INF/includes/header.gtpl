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

<!--    <script src="/js/bootstrap-datepicker.js" type="text/javascript"></script> -->

    <!-- Data Table -->
    <link href="/datatable/css/jquery.dataTables.css">
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

        <!-- Collect the nav links, forms, and other content for toggling -->
<!--
        <div class="collapse navbar-collapse navbar-ex1-collapse">
          <ul class="nav navbar-nav side-nav">

						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-table"></i> Cadastros <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/ranking"><i class="fa fa-user"></i> Ranking</a></li>
								<li><a href="/motoristas"><i class="fa fa-user"></i> Motoristas</a></li>
								<li><a href="/grupos"><i class="fa fa-users"></i> Grupos</a></li>
							</ul>
						</li>
					
						<li class="dropdown">
							<a href="/motoristas" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> Motoristas <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/motorista/add"><i class="fa fa-plus-circle"></i> Novo Motorista</a></li>
							</ul>
						</li>

						<li class="dropdown">
							<a href="/grupos" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-users"></i> Grupos <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/grupo/add"><i class="fa fa-plus-circle"></i> Novo Grupo</a></li>
							</ul>
						</li>
          </ul>
-->
          <ul class="nav navbar-nav navbar-right navbar-user">
						<%if (user != null) {%>
						<li><a href="/ranking"><i class="fa fa-trophy"></i> Ranking</a></li>
						<li><a href="/motoristas"><i class="fa fa-user"></i> Motoristas</a></li>
<!--						<li><a href="/grupos"><i class="fa fa-users"></i> Grupos</a></li>-->
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
