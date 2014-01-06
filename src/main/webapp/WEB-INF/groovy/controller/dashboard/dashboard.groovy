import model.*

request.total_motoristas = new Motoristas().count()
request.dashboard = new Dashboard().get()

forward '/WEB-INF/pages/dashboard/dashboard.gtpl'