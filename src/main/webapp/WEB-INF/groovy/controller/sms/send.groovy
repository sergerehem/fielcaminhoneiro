  import model.*
import util.*

if (user == null) {
    redirect "/"
} else {

    def sms = new SMS()
/*
    def msg = params.texto as String
    msg += msg.endsWidth(".") ? " " : ". "
    msg += params.assinatura
*/
    def msg = params.texto + ((params.texto).endsWith('.') ? " " : ". ") + params.assinatura

    def flush = ""    
    def flushErro = ""
    try {
      def msgId = sms.sendSMS(params.celular, msg)
      def model = new Motoristas()
      def ret = model.addSMS(params.id, params.texto)
      flush = "SMS enviado com sucesso para ${params.celular}! ($msgId)"
    } catch (Exception ex) {
      flushErro = "Erro no envio de SMS para ${params.celular}! (${ex.getMessage()})"
    }

//    println "Sent '${msg}' to $to. MsgId: $msgId. Callback: $callback"
    redirect "/motorista/edit/${params.id}?view=${params.view}&flush=$flush&flushErro=$flushErro"
}
