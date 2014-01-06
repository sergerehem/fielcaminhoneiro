if (user != null) {
      //println "usuario logado " + user.getNickname() 
      //println "<a href='" + users.createLogoutURL(request.getRequestURI()) + "'>sign out</a>"
	forward "/dashboard"
} else {
	url = params.continueTo ?: request.queryString
	redirect users.createLoginURL(url ?: 	request.requestURI,"teste.fielcaminhoneiro.appspot.com") 
}
