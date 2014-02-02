<% include '/WEB-INF/includes/header.gtpl' %>

  <div class="row">
    <h1 class="page-header">Grupos <small>listar</small></h1>
    <a class="btn btn-primary pull-right" href="/grupo/add">Incluir Grupo</a>
    <br><br>
    <div class="table-responsive">
      <table class="table table-striped footable">
        <thead>
           <tr>
              <th>Nome</th>
              <th>Descrição</th>
              <th data-sort-ignore="true">&nbsp;</th>
           </tr>
        </thead>
        <tbody>
           <% request.groups.each { group -> %>
              <tr>
                 <td>${group.name}</td>
                 <td>${group.description}</td>
                 <td>
                  <a class='btn btn-info btn-xs' href="/grupo/edit/${group.key.id}"><i class="fa fa-pencil-square-o"	></i> alterar</a>
                  <a class='btn btn-danger btn-xs' onclick="if (!confirm('Tem certeza que deseja EXCLUIR este Grupo?')) return false;" href="/grupo/delete/${group.key.id}"><i class="fa fa-trash-o"></i> excluir</a>
                  </td>
              </tr>
           <% } %>
        </tbody>
      </table>
    </div>
  </div><!-- /.row -->

<% include '/WEB-INF/includes/footer.gtpl' %>


