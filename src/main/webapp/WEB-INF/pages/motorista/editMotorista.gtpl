<% include '/WEB-INF/includes/header.gtpl' %>
 
<%
  def motorista = request.getAttribute("motorista")
  boolean existingKey = motorista?.key
  String action = !existingKey ? 'Incluir' : request.view ? 'Visualização' : 'Alteração'
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
<%if (params.flush != null) { %>
<div class="alert alert-info alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  $params.flush
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
            <button id="adicionarPontosBtn" class="btn <%if (categoria == "prata"){%>btn-primary<%}else{%>btn-warning<%}%>" data-toggle="modal" data-target="#formAdicionarPontos">
              Adicionar Pontos
            </button>
            <a href="#historico" class="btn">
              Ver Histórico
            </a>

            <a href="#" id="curtiBtn" data-toggle="modal" data-target="#formCurti"><span  class="label label-primary label-lg" style="font-size: 12pt;"><i class="fa fa-thumbs-o-up"></i> <%if (motorista?.curti != null && motorista?.curti != 0){%>${motorista.curti}<%}%></a>&nbsp;
            <a href="#" id="naoCurtiBtn" data-toggle="modal" data-target="#formNaoCurti" class="label label-danger" style="font-size: 12pt;"><i class="fa fa-thumbs-o-down"></i> <%if (motorista?.naoCurti != null && motorista?.naoCurti != 0){%>${motorista.naoCurti}<%}%></a>&nbsp;
            <a href="#" id="smsBtn" data-toggle="modal" data-target="#formSMS" class="label label-info" style="font-size: 12pt;"><i class="fa fa-comment"></i> SMS</a>
        </div>
  </div>
</div>

<% if (request.log != null && request.log.size() > 0) { %>
<div class="panel-group" id="historico">
  <div class="panel panel-default">
    <div class="panel-heading">
      <h4 class="panel-title">
        <a data-toggle="collapse" data-parent="#historico" href="#collapseOne">
          Histórico
          <!--<span id="filtro" class="badge">todos<span>&nbsp;&nbsp;<a href="#" id="limpar" onclick="limpar();">&nbsp;&nbsp;<span class="badge">limpar filtro<span></a>-->
        </a>
          <select id="filtroSelect">
            <option>-- TODOS --</option>
            <option>PONTOS</option>
            <option>BONUS</option>
            <option>CURTI</option>
            <option>NÃO CURTI</option>
          </select>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="table-responsive">
        <table id="tableHistorico" class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="10">
            <thead>
                <th>Data/hora</th>
                <th>Usuário</th>
                <th>Tipo</th>
                <th>Descrição</th>
            </thead>
            <tbody>
            <% request.log.each { log ->
                label = ""
                tipo = ""
                if (log.tipo == "PONTOS") {
                    tipo = 'certificate'
                    label = "$categoria"
                } else if (log.tipo == "BONUS") {
                    tipo = 'money'
                    label = 'label-success'
                } else if (log.tipo == "CURTI") {
                    tipo = 'thumbs-o-up'
                    label = 'label-primary'
                } else if (log.tipo == "NÃO CURTI") {
                    tipo = 'thumbs-o-down'
                    label = 'label-danger'
                } else if (log.tipo == "SMS") {
                    tipo = 'comments'
                    label = 'label-info'
                } else {
                    label = "label-default"
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

<!-- Adicionar Pontos Modal -->
<% if (motorista != null) { %>
<div class="modal fade" id="formAdicionarPontos" tabindex="-1" role="dialog" aria-labelledby="formAdicionarPontosLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Adicionar Pontos</h4>
      </div>
      <div class="modal-body">
      <%
       id = motorista.key.id
       request.pontos.each { ponto ->
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

<div class="modal fade" id="formCurti" tabindex="-1" role="dialog" aria-labelledby="formCurtiLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title"><span class="label label-primary"><i class="fa fa-thumbs-o-up"></i></span> Curti</h3>
      </div>
      <form action="/curti" method="post">
          <fieldset>
          <input type="hidden" name="id" value="${motorista.key.id}">
          <input type="hidden" name="view" value="${request.view}">
          <div class="form-group">
            <textarea name="texto" class="form-control" placeholder="O que você curtiu com relação a esse Motorista?" rows="5" required></textarea>
          </div>
          <div class="panel-footer">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <button type="submit" class="btn btn-labeled btn-success">
                        <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span> Confirmar</a>
                </div>
            </div>
          </div>
          </fieldset>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="formNaoCurti" tabindex="-1" role="dialog" aria-labelledby="formNaoCurtiLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title"><span class="label label-danger"><i class="fa fa-thumbs-o-down"></i></span> Não Curti</h3>
      </div>
      <form action="/naocurti" method="post" class="form">
          <fieldset>
          <input type="hidden" name="id" value="${motorista.key.id}">
          <input type="hidden" name="view" value="${request.view}">
          <div class="form-group">
            <textarea name="texto" class="form-control" placeholder="O que você não curtiu com relação a esse Motorista?" rows="5" required></textarea>
          </div>
          <div class="panel-footer">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <button type="submit" class="btn btn-labeled btn-success">
                        <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span> Confirmar</a>
                </div>
            </div>
          </div>
          </fieldset>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<%}%>

<div class="modal fade" id="formSMS" tabindex="-1" role="dialog" aria-labelledby="formSMSLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title"><span class="label label-info label-lg"><i class="fa fa-comments"></i> SMS</span> Enviar para ${motorista.celular}</h3>
      </div>
      <form action="/sms" method="post" role="form">
        <fieldset>
         <div class="modal-body">
              <input type="hidden" name="id" value="${motorista.key.id}">
              <input type="hidden" name="celular" value="${motorista.celular}">
              <input type="hidden" name="view" value="${request.view}">
              <div class="form-group">
                <textarea name="texto" class="form-control" placeholder="coloque aqui a sua mensagem SMS para o motorista" rows="5" maxlength="110" required></textarea>
                <input class="form-control" id="assinatura" name="assinatura" value="Anisio, 71 9381-3344 (tim), Normando Transportes."></input>
              </div>
         </div>
         <div class="modal-footer">
            <div class="btn-group">
                <button type="submit" class="btn btn-labeled btn-success">
                    <span class="btn-label"><i class="glyphicon glyphicon-ok"></i></span> Enviar</a>
            </div>
     </div>
         </fieldset>
      </form>
        </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%if (request.addPontos == 'true') {%>
<script type="text/javascript">
document.onreadystatechange = function () {
  if (document.readyState === "complete") {
    document.getElementById("adicionarPontosBtn").click();
  }
}
</script>
<%}%>

<%if (request.addCurti == 'true') {%>
<script type="text/javascript">
document.onreadystatechange = function () {
  if (document.readyState === "complete") {
    document.getElementById("curtiBtn").click();
  }
}
</script>
<%}%>

<%if (request.addNaoCurti == 'true') {%>
<script type="text/javascript">
document.onreadystatechange = function () {
  if (document.readyState === "complete") {
    document.getElementById("naoCurtiBtn").click();
  }
}
</script>
<%}%>
$request.sms sms
<%if (request.sms == 'true') {%>
<script type="text/javascript">
document.onreadystatechange = function () {
  if (document.readyState === "complete") {
    document.getElementById("smsBtn").click();
  }
}
</script>
<%}%>


<script type="text/javascript">

/*
(jQuery)(document).ready(function(){
    (jQuery)('#filtro').hide();
    (jQuery)('#limpar').hide();
});
*/

(jQuery)('#filtroSelect').change(function(e) {
    e.preventDefault();
    var filtro = (jQuery)(this).find('option:selected').val();
    //alert(filtro.val());
    if (filtro == "-- TODOS --" )
        limpar();
    else
        filtrar(filtro);
});

function filtrar(filtro) {
    (jQuery)('#tableHistorico').trigger('footable_filter', {filter: filtro});
    /*
    (jQuery)('#filtro').html(filtro);
    (jQuery)('#filtro').show();
    (jQuery)('#limpar').show();
    */
}


function limpar() {
    (jQuery)('#tableHistorico').trigger('footable_clear_filter');
    /*
    (jQuery)('#filtro').html('todos');
    (jQuery)('#filtro').hide();
    (jQuery)('#limpar').hide();
    */
}

</script>
<% include '/WEB-INF/includes/footer.gtpl' %>

