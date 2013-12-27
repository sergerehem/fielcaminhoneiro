import model.*

if (user == null) {
	redirect "/"
} else {
  
  request.motorista = params.id ? new Motoristas().get(params.id) : null
  
  request.groups = new Groups().list()

  request.log = new Logs().listByMotorista(params.id)
  
  forward '/WEB-INF/pages/motorista/editMotorista.gtpl'
}

