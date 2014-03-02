import model.*

if (user == null) {
	redirect "/"
} else {
  request.log = params.tipo ? new Logs().listByTipo(params.tipo) : new Logs().listAll()
  request.title = params.title ?: "Histórico"
  forward '/WEB-INF/pages/log/listLog.gtpl'
}
