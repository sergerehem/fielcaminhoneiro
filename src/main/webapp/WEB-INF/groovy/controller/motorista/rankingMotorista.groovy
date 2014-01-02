import model.*

if (user == null) {
	redirect "/"
} else {
  request.motoristas = new Motoristas().listRanking()
  forward '/WEB-INF/pages/motorista/rankingMotorista.gtpl'
}
