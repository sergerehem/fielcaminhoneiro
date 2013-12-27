import model.*

if (user == null) {
	redirect "/"
} else {
  def model = new Groups()
  request.groups = model.list()

  forward '/WEB-INF/pages/grupo/listGroup.gtpl'
}
