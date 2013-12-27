<% include '/WEB-INF/includes/header.gtpl' %>

  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">Grupos <small>listar</small></h1>

      <a href="/grupo/add">Incluir Grupo</a>
      <br><br>
      <div class="table-responsive">
        <table class="table table-striped">
          <thead>
             <tr>
                <th>Nome</th>
                <th>Descrição</th>
                <th>&nbsp;</th>
             </tr>
          </thead>
          <tbody>
             <% request.groups.each { group -> %>
                <tr>
                   <td>${group.name}</td>
                   <td>${group.description}</td>
                   <td><a href="/grupo/delete/${group.key.id}">Delete</a> | <a href="/grupo/edit/${group.key.id}">Edit</a></td>
                </tr>
             <% } %>
          </tbody>
        </table>
      </div>
       
    </div>      
  </div><!-- /.row -->

<% include '/WEB-INF/includes/footer.gtpl' %>


