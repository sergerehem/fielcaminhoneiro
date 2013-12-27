<% include '/WEB-INF/includes/header.gtpl' %>

	<div class="row">
<!--

FILTER http://bootsnipp.com/snippets/featured/js-table-filter-simple-insensitive

		<div class="col-lg-2">
				<form id="searchForm" action="/motorista/search" method="get">
				<div class="input-group custom-search-form">
          <input type="text" name="search" class="form-control">
          <span class="input-group-btn">
          <button class="btn btn-default" type="button" onclick="searchForm.submit();">
          <span class="glyphicon glyphicon-search"></span>
          </button>
          </span>
      	</div><
				</form>
				<nav class="nav-sidebar">
					  <ul class="nav">
					      <li><a href="/motorista/add"><i class="fa fa-plus"></i> Novo</a></li>

					      <li><a href="javascript:;">About</a></li>
					      <li><a href="javascript:;">Products</a></li>
					      <li><a href="javascript:;">FAQ</a></li>
					      <li class="nav-divider"></li>
					      <li><a href="javascript:;"><i class="glyphicon glyphicon-off"></i> Sign in</a></li>

					  </ul>
				</nav>
		</div>
-->
	  <div class="col-lg-12">
			<h1 class="page-header">Motorista <small>listar</small></h1>
				<div class="row">
					<div class="col-lg-8">
				      <div class="input-group custom-search-form">
				        <input type="text" id="search" name="search" class="form-control" placeholder="procurar motoristas...">
				        <span class="input-group-btn">
				        <button class="btn btn-default" type="button">
				        <span class="glyphicon glyphicon-search"></span>
				       </button>
				       </span>
				       </div><!-- /input-group -->
				  </div>
					<div class="col-lg-4">
						<p class="text-right"><a href="/motorista/add" class="btn btn-primary"><i class="fa fa-plus"></i> Incluir Motorista</a></p>
					</div>
				</div>
			    <div id="tableContatos" class="table-responsive">
			      <table class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="3" data-filter="#search">
			        <thead>
			           <tr>
			              <!--<th>${request.motoristas.size()}</th>-->
			              <th data-sort-initial="true">Nome</th>
			              <th data-sort-ignore="true">Celular</th>
			              <th data-type="numeric">Pontos</th>
			              <th data-sort-ignore="true">Grupo(s)</th>       
			              <th data-sort-ignore="true">&nbsp;</th>
			           </tr>
			        </thead>
			        <tbody>
			           <% request.motoristas.each { motorista -> %>
			              <tr>
			                 <!--<td><a href="/motorista/edit/${motorista.key.id}"><i class="fa fa-pencil-square-o"></i></a>&nbsp;&nbsp;&nbsp;<a href="/motorista/delete/${motorista.key.id}" style="color:red"><i class="fa fa-trash-o"></i></a</td>-->
			                 <td><div class="draggable">${motorista.nome}</div></td>
			                 <td>${motorista.celular}</td>
			                 <td><span class="badge">${motorista.pontos}</span></td>
			                 <td><% motorista.groups.each { group -> %> <a href="#"><span class="badge">$group</span></a><%}%></td>
			                 <td>
<a class='btn btn-primary btn-xs' href="/motorista/view/${motorista.key.id}"><i class="fa fa-eye"></i> ver</a>
<a class='btn btn-info btn-xs' href="/motorista/edit/${motorista.key.id}"><i class="fa fa-pencil-square-o"	></i> alterar</a>
<!--<a class='btn btn-warning btn-xs'href="/motorista/pontos/${motorista.key.id}"><i class="fa fa-truck"></i> pontos de viagem</a>-->
<a class='btn btn-danger btn-xs' href="/motorista/delete/${motorista.key.id}"><i class="fa fa-trash-o"></i> excluir</a>
</td>
			              </tr>
			           <% } %>
			        </tbody>
							<tfoot class="hide-if-no-paging">
								<tr>
									<td colspan="5">
										<div class="pagination pagination-centered"></div>
									</td>
								</tr>
							</tfoot>
			      </table>
			    </div>
				</div>
			</div>
		</div>

		<!--
		<button type="button" class="btn btn-primary btn-circle btn-lg"><i class="fa fa-search"></i></button>
		<button type="button" class="btn btn-success btn-circle btn-lg"><i class="glyphicon glyphicon-link"></i></button>
		<button type="button" class="btn btn-info btn-circle btn-lg"><i class="glyphicon glyphicon-ok"></i></button>
		<button type="button" class="btn btn-warning btn-circle btn-lg"><i class="glyphicon glyphicon-remove"></i></button>
		<button type="button" class="btn btn-danger btn-circle btn-lg"><i class="glyphicon glyphicon-heart"></i></button>
		-->


<% include '/WEB-INF/includes/footer.gtpl' %>


