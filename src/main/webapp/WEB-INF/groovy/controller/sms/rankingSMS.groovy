import com.ocpsoft.pretty.time.PrettyTime
import model.*
import util.*

Date.metaClass.'static'.pretty = { str ->
    new PrettyTime(new Locale("pt")).format(new Date().parse("dd/MM/yyyy kk:mm:ss", str))
}

if (user == null) {
	redirect "/"
} else {
  def motoristas = new Motoristas().listRanking()

  motoristas.eachWithIndex { m, i ->
  
    def cartao =  new Cartao(m.pontos)
      
//    println "${i+1} $m.nome $m.categoria $m.pontos ${cartao.faltaQuantoParaGanharBonus()} ${cartao.valorProximoBonus()} ${cartao.categoriaProximoBonus()} ${Date.pretty(m.dateCreated)}<br>" 
    
    def msg = "Você é o ${i+1}o no ranking do Clube Fiel Caminhoneiro, com $m.pontos pontos. Falta ${cartao.faltaQuantoParaGanharBonus()} para ganhar um bônus de R\$${cartao.valorProximoBonus()}. Sua última viagem foi ${Date.pretty(m.dateCreated)}."
    
    println msg.size() +">$msg<hr>"
  }
  //forward '/WEB-INF/pages/motorista/rankingMotorista.gtpl'
  
}
