import model.*

    
if (user == null) {
    redirect "/"
} else {
    def model = new Motoristas()

    def regiaoPontos = params.optRegiao.split('\\|')
    def regiao = regiaoPontos[0] 
    def pontos = regiaoPontos[1] as Integer 
    def pontosEntregas = params.optEntregas as Integer
    def pontosEstados = params.optEstados as Integer

    def ret = model.addPontos(params.id, pontos, regiao, pontosEstados, pontosEntregas)

    redirect "/motorista/edit/${params.id}?view=${params.view}&pagarBonus=${ret.pagarBonus}&novaCategoria=${ret.novaCategoria}"
}

