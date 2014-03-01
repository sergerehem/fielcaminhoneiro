import model.*
import util.*

if (user == null) {
    redirect "/"
} else {

    def sms = new SMS()
    def msg = params.texto + " " + params.assinatura

    def msgId = sms.sendSMS(params.celular, msg)

//    println "Sent '${msg}' to $to. MsgId: $msgId. Callback: $callback"

    def model = new Motoristas()
    def ret = model.addSMS(params.id, params.texto)
    redirect "/motorista/edit/${params.id}?view=${params.view}&flush=SMS enviado com sucesso para ${params.celular}! ($msgId)"
}
