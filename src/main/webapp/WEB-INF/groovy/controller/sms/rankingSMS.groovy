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
      
    def msg = 'Vc tem '+ m.pontos + ' pontos no Clube Fiel Caminhoneiro (Ultima viagem: ' + m.dateCreated[0..9] + 
    '). Faltam '+ cartao.faltaQuantoParaGanharBonus() + ' p/R.' + cartao.valorProximoBonus() + 
    ' de BONUS. Apareca! Jackson, 71 9189-8470. Normando Transportes.' //${Date.pretty(m.dateCreated)}
    
    if (params.send == 'true') {
      new SMS().sendSMS(m.celular, msg)
      new Motoristas().addSMS(m.key.id as String, msg)
      msg+="... SMS ENVIADO PARA $m.celular!<br>"
    } else {
      msg+="<br>"
    }
    println msg
  }
}
