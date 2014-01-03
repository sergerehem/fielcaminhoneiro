<% include '/WEB-INF/includes/header.gtpl' %>
 
<%
  def motorista = request.getAttribute("motorista")
  boolean existingKey = motorista?.key
  String action = !existingKey ? 'Incluir' : 'Atualizar'
%>

<div class="row">
   <h1>Motorista <small>${action.toLowerCase()}</small></h1>
   <hr>
</div>

<%if (params.novaCategoria != 'null' && params.novaCategoria != null) { %>
<div class="alert alert-warning alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong style="font-size:20pt;">ATENÇÃO!!!</strong> O motorista completou <span style="font-size:20pt;" class="label ${params.novaCategoria}"><i class="fa fa-certificate"></i> ${motorista?.pontos}</span> pontos</strong> e deve mudar para a categoria <span style="font-size:20pt;" class="label ${params.novaCategoria}">${params.novaCategoria}</span>.<br><br>Entregue a ele o seu novo Cartão Clube Fiel Caminhoneiro!
</div>
<%}%>
<%if (params.pagarBonus != 'null' && params.pagarBonus != null) { %>
<div class="alert alert-success alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  <strong style="font-size:20pt;">BÔNUS!!!</strong> Pague agora <span class="label label-success" style="font-size: 18pt;"><i class="fa fa-money"></i> $params.pagarBonus</span> ao motorista porque ele completou <span style="font-size:20pt;" class="label ${motorista.categoria}"><i class="fa fa-certificate"></i> ${motorista?.pontos}</span> pontos!!!</strong>
</div>
<%}%>
<div class="row">
  <div class="col-lg-8">
    <form role="form" class="form-horizontal" action="/motorista/${!existingKey ? 'insert' : 'update'}" method="POST">
        <fieldset<%if (request.view == 'true') {%> disabled<%}%>>
        <div class="form-group">
          <label for="nome" class="col-lg-2 control-label">Nome</label>
          <div class="col-lg-10">
            <input type="text" class="form-control" id="nome" name="nome" value="${motorista?.nome ? motorista.nome : ''}" size="60">
          </div>      
        </div>
        <div class="form-group">
          <label for="celular" class="col-lg-2 control-label">Celular</label>
          <div class="col-lg-6">
            <input type="text" class="form-control phone" id="celular" name="celular" value="${motorista?.celular ? motorista.celular : ''}">
          </div>      
        </div>
        <div class="form-group">
          <label for="groups" class="col-lg-2 control-label">Grupos</label>
          <div class="col-lg-6">
            <select id="groups" name="groups" data-placeholder="Grupos..." class="form-control chosen-select" multiple<%if (request.view) {%> disabled<%}%>>
              <option value=""></option>
                <% request.groups.each { group -> %>
                  <option value="${group.name}" ${motorista && group.name in motorista.groups ? 'selected' : ''}>${group.name}</option>
                <% } %>  
            </select>
          </div>
        </div>
        <%if (request.view != 'true') {%>
        <div class="form-actions">
            <div class="col-lg-offset-2 col-lg-10">
              <p>
                <a href="/motoristas">Cancelar</a>
                <button type="submit" class="btn btn-primary btn-lg"><i class="fa fa-check"></i> ${action}</button>
              </p>
            </div>
        </div>
        <%}%>
        <% if(existingKey) { %>
           <input type="hidden" name="id" value="${motorista.key.id}">
        <% } %>
        </fieldset>
    </form>
  </div>
  <%
  categoria = motorista?.categoria ? motorista.categoria : "bronze"
  if (categoria == "bronze") {
    max = 50000
  } else if (categoria == "prata") {
    max = 150000
  } else {
    max = 300000
  }
  pontos = motorista?.pontos ? motorista.pontos as Integer : 0
  bonus = motorista?.bonus ? motorista.bonus as Integer : 0
  percent = (pontos / max * 100) as Integer
  dif = max - pontos
  %>
  <div class="col-lg-4">
      <div class="thumbnail">
        <%
        %>
        <img src="/img/${categoria}.png" class="img-thumbnail" alt="Clube Fiel Caminhoneiro Bronze">
        <%
        %>
        <div class="progress">
          <div class="progress-bar $categoria" role="progressbar" aria-valuenow="$pontos" aria-valuemin="0" aria-valuemax="49999" style="width: $percent%;">
            <span class="sr-only">$percent% completos</span>
          </div>
        </div>
        <div class="caption">
            <div class="row">
                <div class="col-lg-7">
                    <a href="#historico" onclick="filtrar('PONTOS');" class="btn-link"><span class="label $categoria label-lg" style="font-size: 18pt;"><i class="fa fa-certificate"></i> $pontos</span></a>
                </div>
                <div class="col-lg-5 pull-right">
                    <a href="#historico" onclick="filtrar('BONUS');" class="btn-link"><span class="label label-success" style="font-size: 18pt;"><i class="fa fa-money"></i> $bonus</span></a>
                </div>
            </div>
        </div>
        <div class="caption">
              <p>Faltam <strong>$dif</strong> pontos para mudar de categoria...</p>
            <!-- Button trigger modal -->
            <button id="adicionarPontosBtn" class="btn <%if (categoria == "prata"){%>btn-primary<%}else{%>btn-warning<%}%>" data-toggle="modal" data-target="#myModal">
              Adicionar Pontos
            </button>
            <a href="#historico" class="btn">
              Ver Histórico
            </a>

            <span class="label label-success label-lg" style="font-size: 12pt;"><i class="fa fa-thumbs-o-up"></i> <span class="badge">3</span></span>
            <span class="label label-danger" style="font-size: 12pt;"><i class="fa fa-thumbs-o-down"></i> 1</span>
        </div>
  </div>
</div>

<% if (request.log != null && request.log.size() > 0) { %>
<div class="panel-group" id="historico">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#historico" href="#collapseOne">
          Histórico <span id="filtro" class="badge">todos<span>&nbsp;&nbsp;<a href="#" id="limpar" onclick="limpar();">&nbsp;&nbsp;<span class="badge">limpar filtro<span></a>
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="table-responsive">
        <table id="tableHistorico" class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="10">
            <thead>
                <th data-sort-ignore="true">Data/hora</th>
                <th data-sort-ignore="true">Usuário</th>
                <th data-sort-ignore="true">Tipo</th>
                <th data-sort-ignore="true">Descrição</th>
            </thead>
            <tbody>
            <% request.log.each { log ->
                label = ""
                if (log.tipo == "PONTOS") {
                    tipo = 'certificate'
                    label = "$categoria"
                } else if (log.tipo == "BONUS") {
                    tipo = 'money'
                    label = 'label-success'
                }
                %>
                <tr>
                    <td>$log.dateCreated</td>
                    <td>$log.userEmail</td>
                    <td><span class="label $label"><i class="fa fa-$tipo"></i> $log.tipo<span></td>
                    <td>$log.operacao</td>
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
</div><% } %>

<!-- Modal -->
<% if (motorista != null) { %>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Adicionar Pontos</h4>
      </div>
      <div class="modal-body">
       <% request.pontos.each { ponto ->
            id = motorista.key.id
            pontos = ponto.value
            regiao = ponto.key
            msg = "Confirma a inclusão de $pontos pontos ($regiao)?"
       %>
            <h4><a href="/pontos/add/$id/$pontos?regiao=$regiao&view=${request.view}" onclick="if (!confirm('$msg')) return false;"><span class="badge">$pontos</span> $regiao</a></h4>
       <% } %>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%}%>

<%if (request.addPontos == 'true') {%>
<script type="text/javascript">
document.onreadystatechange = function () {
  if (document.readyState === "complete") {
    document.getElementById("adicionarPontosBtn").click();
  }
}
</script>
<%}%>
<script type="text/javascript">

(jQuery)(document).ready(function(){
    (jQuery)('#filtro').hide();
    (jQuery)('#limpar').hide();
});

function filtrar(filtro) {
    (jQuery)('#tableHistorico').trigger('footable_filter', {filter: filtro});
    (jQuery)('#filtro').html(filtro);
    (jQuery)('#filtro').show();
    (jQuery)('#limpar').show();
}

function limpar() {
    (jQuery)('#tableHistorico').trigger('footable_clear_filter');
    (jQuery)('#filtro').html('todos');
    (jQuery)('#filtro').hide();
    (jQuery)('#limpar').hide();
}
</script>
<% include '/WEB-INF/includes/footer.gtpl' %>

