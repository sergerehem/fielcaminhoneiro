import model.*


if (user == null) {
	redirect "/"
} else {
  request.motoristas = new Motoristas().list()
  
  def grupos = []
  new Groups().list().each { g ->
    grupos << [group:g.name, count:new Motoristas().countByGroup(g.name)]
  }
  
  request.groups = grupos
  
  forward '/WEB-INF/pages/motorista/smsMotoristaSelect.gtpl'
}
