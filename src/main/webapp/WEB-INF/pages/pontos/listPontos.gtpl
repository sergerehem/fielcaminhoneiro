<% include '/WEB-INF/includes/header.gtpl' %>



	<div class="row">
	    <h1 class="page-header">Pontos <small>tabela</small></h1>
    </div>

    <div class="row">
        <div id="tablePontos" class="table-responsive">
          <table class="table table-striped footable default" data-page-navigation=".pagination" data-page-size="20" data-filter="#search">
            <thead>
               <tr>
                  <th>Região</th>
                  <th data-type="numeric">Pontos</th>
               </tr>
            </thead>
            <tbody>
               <% request.pontos.each { pontos -> %>
                  <tr>
                     <td>${pontos.key}</td>
                     <td>${pontos.value}</td>
                  </tr>
               <% } %>
            </tbody>
          </table>
        </div>
    </div>

<% include '/WEB-INF/includes/footer.gtpl' %>


