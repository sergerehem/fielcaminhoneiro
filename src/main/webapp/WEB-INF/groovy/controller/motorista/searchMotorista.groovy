import model.*

if (user == null) {
	redirect "/"
} else {
	if (params.search) {
		String search = params.search
		request.search = search
	  //request.motoristas = new Motoristas().listBySearch(search)

		request.motoristas = search.search {
			select all from motorista
			where nome =~ search
		}
	} else {
	  request.motoristas = new Motoristas().list()
	}
 	forward '/WEB-INF/pages/motorista/listMotorista.gtpl'
}

