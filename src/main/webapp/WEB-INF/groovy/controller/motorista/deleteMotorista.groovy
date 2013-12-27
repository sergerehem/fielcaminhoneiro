import model.*

if (user == null) {
	redirect "/"
} else {
  new Motoristas().delete(params.id)

  redirect '/motoristas'
}
