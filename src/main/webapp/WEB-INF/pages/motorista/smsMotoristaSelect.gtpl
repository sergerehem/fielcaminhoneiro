<% include '/WEB-INF/includes/header.gtpl' %>

  <div class="row">
    <h1 class="page-header">SMS <small>enviar</small></h1>


    <div class="row">
      <form id="formMotoristas" action="/motorista/sms/group" method="post">
        <fieldset>
          <div class="form-group">
            <textarea name="texto" class="form-control" placeholder="coloque aqui a sua mensagem SMS" rows="5" maxlength="110" required></textarea>
            <input class="form-control" id="assinatura" name="assinatura" value="Jackson, 71 9189-8470. Normando Transportes."></input>
          </div>
          
        <div class="form-group">
          <label for="groups" class="col-lg-2 control-label">Grupos/Motoristas</label>
          <div class="col-lg-10">
            <select id="groups" name="groups" data-placeholder="Grupos..." class="form-control chosen-select" multiple<%if (request.view) {%> disabled<%}%>>
              <option disabled>-- GRUPOS --</option>
              <option value=""></option>
                <% request.groups.each { group -> %>
                  <span class="badge"><option value="g_${group.name}">${group.name}</option></span>
                <% } %>  
              <option disabled>-- MOTORISTAS --</option>                
                <% request.motoristas.each { motorista -> %>
                  <option value="m_${motorista.key.id}_${motorista.celular}">${motorista.nome}</option>
                <% } %>  
            </select>
          </div>
        </div>
        <p>&nbsp;</p>                  
         <div class="form-actions">
            <div class="col-lg-offset-10 col-lg-2">
                <button id="btnConfirmarSMS" class="btn btn-lg btn-success ladda-button" data-style="expand-right" data-size="l">
                    <span class="ladda-label">Enviar</span>
                </button>
            </div>
        </div>
       </fieldset>
       </form>
  </div><!-- /.row -->

<% include '/WEB-INF/includes/footer.gtpl' %>

