import util.*

def t = new Twilio()

def to = params.to ?: "+557181069246"
def msg = params.msg ?: "sms test message"
def callback =  params.callback ?: "http://www.fielcaminhoneiro.com.br/twilio_callback"
def msgId = t.sendSMS(to, msg, callback)

println "Sent '${msg}' to $to. MsgId: $msgId. Callback: $callback"