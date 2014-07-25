import model.*
import util.*

if (user == null) {
	redirect "/"
} else {
    def msg = params.texto + ((params.texto).endsWith('.') ? " " : ". ") + params.assinatura

    def smsEnviados = [] 
    def total = 0
    def totalErros = 0
    def msgLog = []
    
    def groups = params.groups as String[] 
    groups.each { p ->
        def sms = new SMS()

        // é um motorista
        if (p.startsWith("m_")) {
            (m, id, cel) = p.tokenize("_")

            if (!(cel in smsEnviados)) {
              try {            
                def msgId = sms.sendSMS(cel, msg)
                new Motoristas().addSMS(id, msg)
                smsEnviados << cel
                msgLog << "SMS enviado para $cel"
                total++
              } catch (Exception ex) {
                msgLog << "SMS **ERRO DE ENVIO** para ${cel} - ${ex.getMessage()}"
                totalErros++
              }
            } 
        } else { // é um grupo
          if (p.startsWith("g_")) {
          
              (g, group) = p.tokenize("_")
              msgLog << "GRUPO: $group"
              
              def motoristas = new Motoristas().listByGroup(group)
              motoristas.each { m ->
                if (!(m.celular in smsEnviados)) {
                  try {
                    def msgId = sms.sendSMS(m.celular, msg)
                    def id = m.key.id as String
                    new Motoristas().addSMS(id, msg)
                    smsEnviados << m.celular 
                    msgLog << "+----> SMS enviado para ${m.celular}"
                    total++
                  } catch (Exception ex) {
                    msgLog << "+----> SMS **ERRO DE ENVIO** para ${m.celular}: ${ex.getMessage()}"
                    totalErros++
                  } 
                }
              }
          }
        }
    }
    
    request.motoristas = new Motoristas().list()
    
    def grupos = []
    new Groups().list().each { g ->
      grupos << [group:g.name, count:new Motoristas().countByGroup(g.name)]
    }
    
    request.groups = grupos    
    
    request.mensagem = msg
    request.msgLog = msgLog
    request.total = total
        
    request.flush = (total > 0) ?"SMS enviado com sucesso para $total motoristas!" : ""

    request.flushErro = (totalErros > 0) ? "$totalErros erros de envio de SMS" : ""

    new SMSLog().add(msg, msgLog as String, total)
      
    forward "/controller/motorista/smsMotoristaSelect.groovy" //"?flush=${request.flush}&flushErro=$flushErro"
}
