import model.*

if (user == null) {
	redirect "/"
} else {
  def model = new Groups()
  model.delete(params.id)

  forward '/controller/grupo/listGroup.groovy'
}
