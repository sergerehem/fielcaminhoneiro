import model.*

if (user == null) {
	redirect "/"
} else {
  new Groups().update(params.id, params.description)
  forward '/controller/grupo/listGroup.groovy'
}