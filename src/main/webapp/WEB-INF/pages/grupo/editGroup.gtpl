<% include '/WEB-INF/includes/header.gtpl' %>

<%
  def group = request.getAttribute("group")
  boolean existingKey = group?.key
  String action = !existingKey ? 'Incluir' : 'Atualizar'
%>

  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">Grupos <small>${action.toLowerCase()}</small></h1>

      <form class="form-horizontal" role="form" action="/grupo/${!existingKey ? 'insert' : 'update'}" method="POST">
        <div class="form-group">
          <label for="name" class="col-lg-2 control-label">Nome</label>
          <div class="col-lg-6">
            <input type="text" class="form-control" id="name" name="name" value="${group?.name ? group.name : ''}" size="30" <%if (existingKey){%>readonly<%}%>>
          </div>      
        </div>
        <div class="form-group">
          <label for="description" class="col-lg-2 control-label">Descrição</label>
          <div class="col-lg-6">
            <input type="text" class="form-control" id="description" name="description" value="${group?.description ? group.description : ''}" size="60">
          </div>      
        </div>
        <div class="form-actions">
          <div class="col-lg-offset-2 col-lg-10">
            <!-- Indicates caution should be taken with this action -->
            <button type="submit" class="btn btn-primary btn-lg">${action}</button>
            <!-- Indicates a dangerous or potentially negative action -->
            <button type="button" class="btn btn-danger" onclick="javascript:document.location.href = '/grupos';">Cancelar</button>
          </div>
        </div>
        <% if(existingKey) { %>
           <input type="hidden" name="id" value="${group.key.id}">
        <% } %>
      </form>
       
    </div>      
  </div><!-- /.row -->

 
<% include '/WEB-INF/includes/footer.gtpl' %>
