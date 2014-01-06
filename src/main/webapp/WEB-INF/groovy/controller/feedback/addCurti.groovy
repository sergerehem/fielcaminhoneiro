import model.*

if (user == null) {
    redirect "/"
} else {
    def model = new Motoristas()
    def ret = model.addCurti(params.id, params.texto)
    redirect "/motorista/edit/${params.id}?view=${params.view}"
}
