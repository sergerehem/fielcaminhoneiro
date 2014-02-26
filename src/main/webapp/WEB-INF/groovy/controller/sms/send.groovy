import model.*
import util.*

if (user == null) {
    redirect "/"
} else {

    def t = new Twilio()
    def cel = "+55" + params.celular.replaceAll('\\(','').replaceAll("\\)","").replaceAll("-","")
    def msg = params.texto + " " + params.assinatura
//    def callback =  params.callback ?: "http://www.fielcaminhoneiro.com.br/twilio_callback"
    def msgId = t.sendSMS(cel, msg)

//    println "Sent '${msg}' to $to. MsgId: $msgId. Callback: $callback"

    def model = new Motoristas()
    def ret = model.addSMS(params.id, params.texto)
    redirect "/motorista/edit/${params.id}?view=${params.view}&flush=SMS enviado com sucesso para ${params.celular}! ($msgId)"
}
