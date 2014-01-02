import model.*

if (user == null) {
    redirect "/"
} else {
    def model = new Motoristas()
    model.addPontos(params.id, params.pontos as Integer, params.regiao)

    request.motorista = params.id

    forward '/controller/motorista/editMotorista.groovy'
}
