import model.*
import util.*

if (user == null) {
	redirect "/"
} else {

    def total = 0
    def totalErro = 0
    
    params.keySet().each { p ->
        if (p.startsWith("m_")) {
            (m, id, cel) = p.tokenize("_")

            def sms = new SMS()
            def msg = params.texto + ((params.texto).endsWith('.') ? " " : ". ") + params.assinatura

            try {
              def msgId = sms.sendSMS(cel, msg)
              def ret = new Motoristas().addSMS(id, msg)
              total++
            } catch (Exception ex) {
              totalErros++
            }        
    }

    request.flush = (total > 0) ?"SMS enviado com sucesso para $total motoristas!" : ""
    request.flushErro = (totalErros > 0) ? "$totalErros erros de envio de SMS" : ""
    
    forward "/controller/motorista/listMotorista.groovy?flush=${request.flush}&flushErro=${request.flushErro}"
    
    //forward '/WEB-INF/pages/motorista/listMotorista.gtpl'
}

