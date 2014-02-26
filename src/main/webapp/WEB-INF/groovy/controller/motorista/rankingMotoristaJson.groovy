import model.*

def motoristas = new Motoristas().listRanking()
motoristas.each { m ->
  println m
}
