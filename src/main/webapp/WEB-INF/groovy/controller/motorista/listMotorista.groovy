import model.*

if (user == null) {
	redirect "/"
} else {
  if (params.group) {
    request.filter = "grupo $params.group"
    request.motoristas = new Motoristas().listByGroup(params.group)
  } else {
    if (params.pontos) {
        request.filter = "que possuem pontos"
        request.motoristas = new Motoristas().listByPontos()
    } else if (params.bonus == 'true') {
        request.filter = "que já ganharam bônus"
        request.motoristas = new Motoristas().listByBonus()
    } else {
        request.filter = "todos"
        request.motoristas = new Motoristas().list()
    }
  }
  
  def groups = []
  new Groups().list().each { g ->
    groups << [name:g.name, value: new Motoristas().countByGroup(g.name)]
  }
  request.groups = groups
  
  request.group = params.group

  request.flush = params.flush

  forward '/WEB-INF/pages/motorista/listMotorista.gtpl'
}
