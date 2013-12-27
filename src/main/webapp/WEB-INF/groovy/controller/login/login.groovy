if (user != null) {
      //println "usuario logado " + user.getNickname() 
      //println "<a href='" + users.createLogoutURL(request.getRequestURI()) + "'>sign out</a>"
	forward "/"
} else {
	url = params.continueTo ?: request.queryString
	redirect users.createLoginURL(url ?: 	request.requestURI,"www.normandotransportes.com.br") 
}
