import model.*

if (user == null) {
	redirect "/"
} else {
  def model = new Motoristas()
  model.add(params.nome, params.celular, params.pontos, params.groups)

  redirect '/controller/motorista/listMotorista.groovy'
}
