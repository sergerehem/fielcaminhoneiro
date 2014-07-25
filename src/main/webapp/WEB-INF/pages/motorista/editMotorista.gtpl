
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

<%if (request.isAniversario) { %>
<div class="alert alert-warning alert-dismissable">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
    <h4><img src="/img/aniversario.png" class="img-circle" alt="Aniversaŕio"></i> Aniversário!</h4>    
    Hoje é o <strong>aniversário</strong> do motorista! Dê o PARABÉNS a ele!
</div>
<%}%>
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
<%if (params.flush != "" && params.flush != null) { %>
<div class="alert alert-info alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  $params.flush
</div>
<%}%>
<%if (params.flushErro != "" && params.flush != null && params.flush != "null") { %>
<div class="alert alert-danger alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  $params.flushErro
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
          <label for="dataNascimento" class="col-lg-2 control-label">Data de Nascimento</label>
          <div class="span5 col-lg-6" id="sandbox-container">
            <div class="input-group date">
              <input id="dataNascimento" name="dataNascimento" type="text" value="${motorista?.dataNascimento ? motorista.dataNascimento : ''}" class="form-control dateMask"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
            </div>
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

        <div class="form-actions">
            <div class="col-lg-offset-2 col-lg-10">
              <p>
                <a href="/motoristas"><i class="fa fa-arrow-left"></i> Voltar</a>&nbsp;
                <%if (request.view != 'true') {%>
                <button type="submit" class="btn btn-primary btn-lg"><i class="fa fa-check"></i> ${action}</button>
                <%}%>
              </p>
            </div>
        </div>

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
              <i class="fa fa-plus-circle"></i> Pontos
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
            <option>SMS</option>
          </select>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in">
      <div class="table-responsive">
        <table id="tableHistorico" class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="10">
            <thead>
                <th data-sort-initial="descending">Data/hora</th>
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
    <form method="post" action="/pontos/add" id="formPontos" name="formPontos">
    <fieldset>  
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">Adicionar Pontos</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-md-9">
            <div class="btn-group">
             <label>Qual a região de destino?</label>
            <%
             id = motorista.key.id
             %>
             <input type="hidden" name="id" value="$id"/>
             <input type="hidden" name="regiao"/>
             <!--<input type="hidden" name="pontosEstados"/>
             <input type="hidden" name="pontosEntregas"/>-->
             <%
             request.pontos.each { ponto ->
                  pontos = ponto.value
                  regiao = ponto.key
                  msg = "Confirma a inclusão de $pontos pontos ($regiao)?"
             %>
                  <!--<h4><a href="/pontos/add/$id/$pontos?regiao=$regiao&view=${request.view}" onclick="if (!confirm('$msg')) return false;"><span class="badge">$pontos</span> $regiao</a></h4>-->
              <div class="radio">
                  <label>
                      <input type="radio" name="optRegiao" value="$regiao|$pontos"><span class="badge">$pontos</span> $regiao<br/>
                  </label>
              </div>            
             <% } %>
             </div>
           </div>
           <div class="col-md-3">
             <span class="label prata label-lg" style="font-size: 18pt;"><i class="fa fa-plus-circle"></i> <span id="pontosAdd">0</span></span>
           </div>
         </div>
         <div class="row">
             <div class="col-md-6">
                <div class="btn-group">
                   <label>Quantos Estados?</label>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optEstados" value="0"><span class="badge">+0</span> 1
                        </label>
                    </div>            
                    <div class="radio">
                        <label>
                            <input type="radio" name="optEstados" value="500"><span class="badge">+500</span> 2
                        </label>
                    </div>            
                    <div class="radio">
                        <label>
                            <input type="radio" name="optEstados" value="1000"><span class="badge">+1000</span> 3 ou mais
                        </label>
                    </div>            
                </div>
            </div>
            <div class="col-md-6">
              <div class="btn-group">
                 <label>Quantas Entregas?</label>
                  <div class="radio">
                      <label>
                          <input type="radio" name="optEntregas" value="0"><span class="badge">+0</span> Até 4
                      </label>
                  </div>            
                  <div class="radio">
                      <label>
                          <input type="radio" name="optEntregas" value="500"><span class="badge">+500</span> 5 ou 6
                      </label>
                  </div>            
                  <div class="radio">
                      <label>
                          <input type="radio" name="optEntregas" value="1000"><span class="badge">+1000</span> 7 ou mais
                      </label>
                  </div>            
              </div>      
          </div>
      </div>        
    </div>
    <!-- /.modal-content -->
     <div class="modal-footer">
        <div class="btn-group">
            <button type="button" class="btn btn-lg btn-success" onclick="submitPontos();">
                <i class="fa fa-check"></i></span> Confirmar
            </button>
        </div>
    </div>     
    </fieldset>
    </form>
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
           <div class="modal-body">
                <input type="hidden" name="id" value="${motorista.key.id}">
              <input type="hidden" name="view" value="${request.view}">
              <div class="form-group">
                <textarea name="texto" class="form-control" placeholder="O que você curtiu com relação ao motorista $motorista.nome?" rows="5" required></textarea>
              </div>
             </div>
         <div class="modal-footer">
            <div class="btn-group">
                <button type="submit" class="btn btn-lg btn-success">
                    <i class="fa fa-check"></i></span> Confirmar
                </button>
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
              <div class="modal-body">
              <input type="hidden" name="id" value="${motorista.key.id}">
              <input type="hidden" name="view" value="${request.view}">
              <div class="form-group">
                <textarea name="texto" class="form-control" placeholder="O que você não curtiu com relação a ao motorista $motorista.nome?" rows="5" required></textarea>
              </div>
          </div>
          <div class="modal-footer">
            <div class="btn-group">
                <button type="submit" class="btn btn-lg btn-success">
                    <i class="fa fa-check"></i></span> Confirmar
                </button>
            </div>
          </div>
          </fieldset>
      </form>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="formSMS" tabindex="-1" role="dialog" aria-labelledby="formSMSLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title"><span class="label label-info label-lg"><i class="fa fa-comments"></i> SMS</span> Enviar para ${motorista.nome} <span class="badge">${motorista.celular}<span></h3>
      </div>
      <form action="/sms/send" method="post" role="form">
        <fieldset>
         <div class="modal-body">
              <input type="hidden" name="id" value="${motorista.key.id}">
              <input type="hidden" name="celular" value="${motorista.celular}">
              <input type="hidden" name="view" value="${request.view}">
              <div class="form-group">
                <textarea name="texto" class="form-control" placeholder="coloque aqui a sua mensagem SMS para o motorista $motorista.nome" rows="5" maxlength="110" required></textarea>
                <input class="form-control" id="assinatura" name="assinatura" value="Jackson, 71 9189-8470. Normando Transportes."></input>
              </div>
         </div>
         <div class="modal-footer">
            <!--<div class="btn-group">
                <button type="button" class="btn btn-primary pull-left">
                    <i class="fa fa-copy"></i> Copiar Texto
                </button>&nbsp;
            </div>-->
            <div class="btn-group">
                <button type="submit" class="btn btn-lg btn-success">
                    <i class="fa fa-check"></i> Enviar
                </button>
            </div>
        </div>
         </fieldset>
      </form>
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

//(jQuery)('#optRegiao').change(function() {       
(jQuery)("input[type='radio']").change(function () {
  console.log('Mudou para'+(jQuery)(this).val());
  //(jQuery)('#pontosAdd').text(somaPontosAdicionais());
  somaPontosAdicionais();
  //alert((jQuery)(this).val());
});

function somaPontosAdicionais() {
  var optRegiao = (jQuery)('input[name=optRegiao]:checked', '#formPontos').val()
  var optEstados = (jQuery)('input[name=optEstados]:checked', '#formPontos').val()  
  var optEntregas = (jQuery)('input[name=optEntregas]:checked', '#formPontos').val() 
  if (optRegiao || optEstados || optEntregas) {
    console.log('deu');
    var pontosRegiao = optRegiao ? optRegiao.split('|')[1] : 0;
    var somaPontos = parseInt(pontosRegiao) + (optEstados ? parseInt(optEstados) : 0) + (optEntregas ? parseInt(optEntregas) : 0);
    console.log(somaPontos);
    (jQuery)('#pontosAdd').text(somaPontos);
    (jQuery)('#pontosAdd').show();    
  } else {
   console.log('creca');
    (jQuery)('#pontosAdd').text('0');
    (jQuery)('#pontosAdd').hide();
  }  
}

function filtrar(filtro) {
    (jQuery)('#tableHistorico').trigger('footable_filter', {filter: filtro});
    /*
    (jQuery)('#filtro').html(filtro);
    (jQuery)('#filtro').show();
    (jQuery)('#limpar').show();
    */
}

function submitPontos() {
//  var regiao = (jQuery)('#optRegiao').value();
  var optRegiao = (jQuery)('input[name=optRegiao]:checked', '#formPontos').val()
  var optEstados = (jQuery)('input[name=optEstados]:checked', '#formPontos').val()
  var optEntregas = (jQuery)('input[name=optEntregas]:checked', '#formPontos').val()      
  if (optRegiao && optEstados && optEntregas) {
      var regiaoPontos = optRegiao.split("|");
      var regiao = regiaoPontos[0];
      var pontos = regiaoPontos[1];
      //alert(regiao+"->"+pontos + "->" + optEstados + "->" + optEntregas);
      document.formPontos.submit();
  } else
    alert('Preencha todas as informações!');
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

