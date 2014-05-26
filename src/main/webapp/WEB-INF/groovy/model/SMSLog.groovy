package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class SMSLog {

    def get(id) {
        def longId = Long.parseLong(id)
        Key key = KeyFactory.createKey("smslog", longId)
        def goal = datastore.get(key)
    }

    def listAll() {
        datastore.execute {
            select all
            from smslog
            sort desc by dateCreated
        }
    }

    def add(mensagem, msgLog, total) {
        datastore.withTransaction {
            def e = new Entity("smslog")
            e.mensagem = mensagem
            e.total = total
            e.msgLog = msgLog
            e.userEmail = user.nickname
            e.dateCreated = (new Clock()).getDateTime()
            e.save()
        }
    }
}
