import model.*

if (user == null) {
	redirect "/"
} else {
  new Motoristas().update(params.id, params.nome, params.celular, params.dataNascimento, params.groups)
  forward '/controller/motorista/listMotorista.groovy'
}




