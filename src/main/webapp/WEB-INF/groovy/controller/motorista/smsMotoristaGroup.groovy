import model.*
import util.*

if (user == null) {
	redirect "/"
} else {
    def msg = params.texto + ((params.texto).endsWith('.') ? " " : ". ") + params.assinatura

    println "ENVIO DE SMS NORMANDO TRANSPORTES<br>" 
    println "<hr>"
    println "MENSAGEM: $msg" 
    println "<hr>"

    def total = 0
    params.groups.each { p ->
        def sms = new SMS()

        // é um motorista
        if (p.startsWith("m_")) {
            (m, id, cel) = p.tokenize("_")
            def msgId = sms.sendSMS(cel, msg)
            def ret = new Motoristas().addSMS(id, msg)
            
            println "MOTORISTA: SMS enviado para $cel<br>"
            
            total++
        } else { // é um grupo
          if (p.startsWith("g_")) {
          
              (g, group) = p.tokenize("_")
              println "GRUPO: $group<br>"
              
              def motoristas = new Motoristas().listByGroup(params.group)
              motoristas.each { m ->
                println "...SMS enviando para $m.celular<br>"
                def msgId = sms.sendSMS(m.celular, msg)
                def ret = new Motoristas().addSMS(m.key.id, msg)
                total++
              }
          }
        }
    }
    println "<hr>"    
    println "$total SMS enviados com sucesso!"

    //request.flush = "SMS enviado com sucesso para $total motoristas!"

    //forward "/controller/motorista/listMotorista.groovy?flush=SMS enviado com sucesso para $total motoristas"

}

