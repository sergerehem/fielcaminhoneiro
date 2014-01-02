import model.*

if (user == null) {
	redirect "/"
} else {
  request.pontos = new Pontos().list()
  forward '/WEB-INF/pages/pontos/listPontos.gtpl'
}
