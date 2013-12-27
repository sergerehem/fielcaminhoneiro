import model.*

if (user == null) {
	redirect "/"
} else {
  if (params.group) {
    request.motoristas = new Motoristas().listByGroup(params.group)
  } else {
    request.motoristas = new Motoristas().list()
  }
  
  def groups = []
  new Groups().list().each { g ->
    groups << [name:g.name, value: new Motoristas().countByGroup(g.name)]
  }
  request.groups = groups
  
  request.group = params.group
  
  forward '/WEB-INF/pages/motorista/listMotorista.gtpl'
}
