import model.*

request.total_motoristas = new Motoristas().count()
request.total_ranking = new Motoristas().listRanking().size()
request.dashboard = new Dashboard().get()

forward '/WEB-INF/pages/dashboard/dashboard.gtpl'