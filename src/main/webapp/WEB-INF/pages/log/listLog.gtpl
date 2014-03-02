<% include '/WEB-INF/includes/header.gtpl' %>

  <div class="row">
    <h1 class="page-header">$request.title <small>listar</small></h1>
    <div class="table-responsive">
        <table id="tableHistorico" class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="100">
            <thead>
                <th data-sort-initial="true">Data/hora</th>
                <th>Usuário</th>
                <th>Tipo</th>
                <th>Descrição</th>
                <th>Motorista</th>
            </thead>
            <tbody>
            <% request.log.each { log ->

              categoria = "bronze"

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
                     <td><a href="/motorista/view/$log.idMotorista">$log.nomeMotorista</a></td>
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
  </div><!-- /.row -->

<% include '/WEB-INF/includes/footer.gtpl' %>


