import model.*

if (user == null) {
    redirect "/"
} else {
    def model = new Motoristas()
    def ret = model.addPontos(params.id, params.pontos as Integer, params.regiao)

    redirect "/motorista/edit/${params.id}?view=${params.view}&pagarBonus=${ret.pagarBonus}&novaCategoria=${ret.novaCategoria}"
}
