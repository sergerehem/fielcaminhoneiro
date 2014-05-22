import model.*


if (user == null) {
	redirect "/"
} else {
  request.motoristas = new Motoristas().list()
  request.groups = new Groups().list()
  
  forward '/WEB-INF/pages/motorista/smsMotoristaSelect.gtpl'
}
