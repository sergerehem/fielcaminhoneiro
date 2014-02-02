<% include '/WEB-INF/includes/header.gtpl' %>
<div class="row">
  <h1 class="page-header">Motorista <small><%=request.filter%></small></h1>
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
  <div class="col-md-4 col-md-offset-1">
    <a href="/motorista/add" class="btn btn-primary pull-right"><i class="fa fa-plus"></i> Incluir Motorista</a>
  </div>
</div>

<div class="row">
    <div id="tableContatos" class="table-responsive">
      <table class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="15" data-filter="#search">
        <thead>
           <tr>
              <th<%if (!request.filter){%>data-sort-initial="true"<%}%>>Nome</th>
              <th data-sort-ignore="true">Celular</th>
              <th data-sort-ignore="true">Cartão</th>
              <th data-type="numeric">Pontos</th>
              <th data-type="numeric">Bônus</th>
              <th data-sort-ignore="true">Grupo(s)</th>
              <th data-sort-ignore="true">&nbsp;</th>
           </tr>
        </thead>
        <tbody>
           <% request.motoristas.each { motorista -> %>
              <tr>
                 <td><div class="draggable">${motorista.nome}</div></td>
                 <td>${motorista.celular}</td>
                 <td><img src="/img/${motorista.categoria}_mini.png" class="img-rounded" alt="${motorista.categoria}"/></td>
                 <td><span class="badge ${motorista.categoria}"><i class="fa fa-certificate"></i> ${motorista.pontos}</span></td>
                 <td><span class="badge bonus"><i class="fa fa-money"></i> ${motorista.bonus}</span></td>
                 <td><% motorista.groups.each { group -> %> <a href="#"><span class="badge">$group</span></a><%}%></td>
                 <td>
                <a class='btn btn-primary btn-xs' href="/motorista/view/${motorista.key.id}"><i class="fa fa-eye"></i> ver</a>
                <a class='btn btn-info btn-xs' href="/motorista/edit/${motorista.key.id}"><i class="fa fa-pencil-square-o"	></i> alterar</a>
                <a class='btn btn-danger btn-xs' onclick="if (!confirm('Todos os dados deste motorista serão excluídos. Tem certeza que deseja EXCLUIR o motorista?')) return false;" href="/motorista/delete/${motorista.key.id}"><i class="fa fa-trash-o"></i> excluir</a>
                &nbsp;|&nbsp;
                <a class='btn btn-warning btn-xs' href="/motorista/view/${motorista.key.id}?addPontos=true"><i class="fa fa-certificate"></i> adicionar pontos</a>
                <a class='btn btn-primary btn-xs' href="/motorista/view/${motorista.key.id}?addCurti=true"><i class="fa fa-thumbs-o-up"></i> <%if (motorista.curti != null && motorista.curti != 0){%> ${motorista.curti}</span><%}%></a>
                <a class='btn btn-danger btn-xs' href="/motorista/view/${motorista.key.id}?addNaoCurti=true"><i class="fa fa-thumbs-o-down"></i> <%if (motorista.naoCurti != null && motorista.naoCurti != 0){%> ${motorista.naoCurti}<%}%></a>
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

<% include '/WEB-INF/includes/footer.gtpl' %>


