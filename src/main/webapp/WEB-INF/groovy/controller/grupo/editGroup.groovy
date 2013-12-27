import model.*

if (user == null) {
	redirect "/"
} else {
  
  request.group = params.id ? new Groups().get(params.id) : null
  
  forward '/WEB-INF/pages/grupo/editGroup.gtpl'
}
