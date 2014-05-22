import model.*
import util.*

if (user == null) {
	redirect "/"
} else {

    def total = 0
    params.keySet().each { p ->
        if (p.startsWith("m_")) {
            (m, id, cel) = p.tokenize("_")

            def sms = new SMS()
            def msg = params.texto + ((params.texto).endsWith('.') ? " " : ". ") + params.assinatura

            def msgId = sms.sendSMS(cel, msg)
            def ret = new Motoristas().addSMS(id, msg)

            total++
        }
    }


    request.flush = "SMS enviado com sucesso para $total motoristas!"

    forward "/controller/motorista/listMotorista.groovy?flush=SMS enviado com sucesso para $total motoristas"
    //forward '/WEB-INF/pages/motorista/listMotorista.gtpl'
}

