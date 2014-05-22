<% include '/WEB-INF/includes/header.gtpl' %>
<div class="row">
  <h1 class="page-header">Motorista <small><%=request.filter%></small></h1>
</div>

<form id="formMotoristas" action="/motorista/sms/send" method="post">

<%if (params.flush != null) { %>
<div class="alert alert-info alert-dismissable">
  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
  $params.flush
</div>
<%}%>

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
    <button id="btnEnviarSMS" class="btn btn-info"  data-toggle="modal" data-target="#formSMS" disabled><i class="fa fa-comments"></i> SMS <span id="qtdSMS" class="badge" style="display:none">2</span></button>
    <a href="/motorista/add" class="btn btn-primary pull-right"><i class="fa fa-plus"></i> Incluir Motorista</a>
  </div>
</div>

<div class="row">
    <div id="divTableMotoristas" class="table-responsive">

      <table id="tableMotoristas" class="table table-striped footable intercept default" data-page-navigation=".pagination" data-page-size="20" data-filter="#search">
        <thead>
           <tr>
              <td data-sort-ignore="true"><input type="checkbox" id ="chckHead"/></td>
              <th<%if (!request.filter){%>data-sort-initial="true"<%}%>>Nome</th>
              <th>Celular</th>
              <th data-sort-ignore="true">Cartão</th>
              <th data-type="numeric">Pontos</th>
              <th data-type="numeric">Bônus</th>
              <th>Grupo(s)</th>
              <th data-sort-ignore="true">&nbsp;</th>
           </tr>
        </thead>
        <tbody>
           <% request.motoristas.each { motorista -> %>
              <tr>
                 <td><input type="checkbox" class = "chcktbl" id="chckRow" name="m_${motorista.key.id}_${motorista.celular}"/></td>
                 <td><div class="draggable"><a href="/motorista/view/${motorista.key.id}">${motorista.nome}</a></div></td>
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
                <a class='btn btn-info btn-xs' href="/motorista/view/${motorista.key.id}?sms=true"><i class="fa fa-comments"></i> enviar SMS</a>
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
      <p>Total: ${request.motoristas.size()} Motoristas
    </div>
</div>

<div class="modal fade" id="formSMS" tabindex="-1" role="dialog" aria-labelledby="formSMSLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h3 class="modal-title"><span class="label label-info label-lg"><i class="fa fa-comments"></i> SMS</span> Enviar</h3>
      </div>
        <fieldset>
         <div class="modal-body">
              <div class="form-group">
                <textarea name="texto" class="form-control" placeholder="coloque aqui a sua mensagem SMS para os motoristas selecionados" rows="5" maxlength="110" required></textarea>
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
                <button id="btnConfirmarSMS" class="btn btn-lg btn-success ladda-button" data-style="expand-right" data-size="l">
                    <span class="ladda-label">Enviar</span>
                </button>
            </div>
        </div>
         </fieldset>
        </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</form>

<script type="text/javascript">

    (jQuery)(function () {
        update();
    });

    (jQuery)('.chcktbl').click(function () {
        update();
    });

    function update() {
        var length = (jQuery)('.chcktbl:visible:checked').length;
        if (length > 0) {
            (jQuery)("#qtdSMS").show();
            (jQuery)("#qtdSMS").html(length);
            (jQuery)('#btnEnviarSMS').removeAttr('disabled');
        }
        else {
            (jQuery)("#qtdSMS").hide();
            (jQuery)('#btnEnviarSMS').attr('disabled', 'disabled');
        }
    }
    (jQuery)('#chckHead').click(function () {

        if (this.checked == false) {

            //(jQuery)('.chcktbl:checked').attr('checked', false);
            (jQuery)('.chcktbl').removeAttr('checked');
        }
        else {
            (jQuery)('.chcktbl:not(:checked)').attr('checked', true);
        }
        update();
    });

    (jQuery)('#btnConfirmarSMS').click(function () {
        console.log('vai');
        (jQuery)("#formMotoristas").submit();
    });

     (jQuery)(function () {

        // Bind normal buttons
        Ladda.bind( 'div:not(.progress-demo) button', { timeout: 1000 } );

        //Ladda.bind( '#btnConfirmarSMS' );

        (jQuery)('table.intercept').bind({
                'footable_sorting': function (e) {
                    //return confirm('Do you want to sort by column: ' + e.column.name + ', direction: ' + e.direction);
                },
                'footable_filtered  ': function (e) {
                    update();
                    //alert('filter');
                },
                'footable_paging': function (e) {
                    //update();
                }
            });
        });

</script>

<% include '/WEB-INF/includes/footer.gtpl' %>
