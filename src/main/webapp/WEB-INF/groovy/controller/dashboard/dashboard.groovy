import model.*

request.total_motoristas = new Motoristas().count()
request.total_ranking = new Motoristas().countRanking()
request.total_bonus = new Logs().count("BONUS")
request.total_sms = new Logs().count("SMS")
request.total_dist_pontos = new Logs().count("PONTOS")
request.dashboard = new Dashboard().get()

forward '/WEB-INF/pages/dashboard/dashboard.gtpl'
