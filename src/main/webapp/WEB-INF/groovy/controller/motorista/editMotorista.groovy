import model.*

if (user == null) {
	redirect "/"
} else {

  if (params.id) {
    request.motorista = new Motoristas().get(params.id)
    request.log = new Logs().listByMotorista(params.id)
  } else {
    request.motorista = null
  }

  request.groups = new Groups().list()

  request.pontos = new Pontos().list()

  request.view = params.view ?: false

  request.addPontos = params.addPontos ?: false

  forward '/WEB-INF/pages/motorista/editMotorista.gtpl'
}

