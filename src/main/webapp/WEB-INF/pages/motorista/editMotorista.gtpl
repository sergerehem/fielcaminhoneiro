<% include '/WEB-INF/includes/header.gtpl' %>
 
<%
  def motorista = request.getAttribute("motorista")
  boolean existingKey = motorista?.key
  String action = !existingKey ? 'Incluir' : 'Atualizar'
%>
 
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Motorista <small>${action.toLowerCase()}</small></h1>
<!--
<div class="progress">
  <div class="progress-bar progress-bar-success" style="width: 35%"></div>
  <div class="progress-bar progress-bar-warning" style="width: 20%"></div>
  <div class="progress-bar progress-bar-danger" style="width: 10%"></div>
</div>
-->
    <form role="form" class="form-horizontal" action="/motorista/${!existingKey ? 'insert' : 'update'}" method="POST">
        <div class="form-group">
          <label for="nome" class="col-lg-2 control-label">Nome</label>
          <div class="col-lg-6">
            <input type="text" class="form-control" id="nome" name="nome" value="${motorista?.nome ? motorista.nome : ''}" size="60">
          </div>      
        </div>

<!--
          <label for="apelido" class="col-lg-1 control-label">Apelido</label>
          <div class="col-lg-3">
            <input type="text" class="form-control" id="apelido" name="apelido" value="${motorista?.apelido ? motorista.apelido : ''}" size="30">
          </div> 
        </div>

        <div class="form-group">
          <label class="col-lg-2 control-label" for="endereço">Endereco</label>
          <div class="col-lg-6">
            <textarea id="endereco" name="endereco" rows="3" placeholder="" class="form-control">${motorista?.endereco ? motorista.endereco : ''}</textarea>
          </div>
          <label for="estado" class="col-lg-1 control-label">Estado</label>
          <div class="col-lg-3">
            <input type="text" class="form-control" id="estado" name="estado" value="${motorista?.estado ? motorista.estado : ''}" size="2">
          </div>              
          <br/><br/>       
          <label for="apelido" class="col-lg-1 control-label">Cidade</label>
          <div class="col-lg-3">
            <input type="text" class="form-control" id="cidade" name="cidade" value="${motorista?.cidade ? motorista.cidade : ''}" size="30">
          </div>  
        </div>
-->
        <div class="form-group">
          <label for="celular" class="col-lg-2 control-label">Celular</label>
          <div class="col-lg-6">
            <input type="text" class="form-control phone" id="celular" name="celular" value="${motorista?.celular ? motorista.celular : ''}">
          </div>      
        </div>

        <div class="form-group">
          <label for="pontos" class="col-lg-2 control-label">Pontos</label>
          <div class="col-lg-6">
            <input type="text" class="form-control" id="celular" name="pontos" value="${motorista?.pontos ? motorista.pontos : ''}">
          </div>      
        </div>

        <div class="form-group">
          <label for="groups" class="col-lg-2 control-label">Grupos</label>
          <div class="col-lg-6">
            <select id="groups" name="groups" data-placeholder="Grupos..." class="form-control chosen-select" multiple tabindex="-1">
              <option value=""></option>
                <% request.groups.each { group -> %>
                  <option value="${group.name}" ${motorista && group.name in motorista.groups ? 'selected' : ''}>${group.name}</option>
                <% } %>  
            </select>
          </div>

      </div>    
      
      <div class="form-actions">
        <div class="col-lg-offset-2 col-lg-10">
          <p>
            <a href="/motoristas">Cancelar</a>
            <button type="submit" class="btn btn-primary btn-lg"><i class="fa fa-check"></i> ${action}</button>
          </p>
        </div>
      </div>             
      <% if(existingKey) { %>
         <input type="hidden" name="id" value="${motorista.key.id}">
      <% } %>
    </form>
  </div><!-- /.row -->
</div><!-- /.container -->

<% include '/WEB-INF/includes/footer.gtpl' %>
