<% include '/WEB-INF/includes/header.gtpl' %>



	<div class="row">
	    <h1 class="page-header">Motoristas <small>ranking</small></h1>
    </div>

    <div class="row">
        <div class="col-md-7">
          <div class="input-group custom-search-form">
            <input type="text" id="search" name="search" class="form-control" placeholder="procurar...">
            <span class="input-group-btn">
                <button class="btn btn-default" type="button">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </span>
          </div> <!--/input-group -->
        </div>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
    </div>

    <div class="row">
        <div id="tableContatos" class="table-responsive">
          <table class="table table-striped footable default" data-filter="#search">
            <thead>
               <tr>
                  <th data-sort-ignore="true">#</th>
                  <th data-sort-ignore="true">Nome</th>
                  <th data-sort-ignore="true">Pontos</th>
                  <th data-sort-ignore="true">Bônus</th>
                  <th data-sort-ignore="true">Feedback</th>
               </tr>
            </thead>
            <tbody>
               <% request.motoristas.eachWithIndex { motorista, i -> %>
                  <tr>
                     <td>${i+1}</td>
                     <td><a href="/motorista/view/${motorista.key.id}">${motorista.nome}</a></td>
                     <td><span class="badge ${motorista.categoria}"><i class="fa fa-certificate"></i> ${motorista.pontos}</span></td>
                     <td><span class="badge bonus"><i class="fa fa-money"></i> ${motorista.bonus}</span></td>
                     <td>
                        <%if (motorista.curti!=null && motorista.curti!=0) {%><span class="label label-primary"><i class="fa fa-thumbs-o-up"></i> ${motorista.curti}</span><%}%>
                        <%if (motorista.naoCurti!=null && motorista.naoCurti!=0) {%><span class="label label-danger"><i class="fa fa-thumbs-o-down"></i> ${motorista.naoCurti}</span><%}%></td>
                     </td>
                  </tr>
               <% } %>
            </tbody>
          </table>
        </div>
    </div>

<% include '/WEB-INF/includes/footer.gtpl' %>


