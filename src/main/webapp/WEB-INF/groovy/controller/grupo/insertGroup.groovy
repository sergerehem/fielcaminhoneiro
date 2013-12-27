import model.*

if (user == null) {
	redirect "/"
} else {
  def model = new Groups()
  model.add(params.name, params.description)

  redirect '/controller/grupo/listGroup.groovy'
}
