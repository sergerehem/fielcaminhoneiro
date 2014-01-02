package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class Logs {

    def get(id) {
        def longId = Long.parseLong(id)
        Key key = KeyFactory.createKey("motorista", longId)
        def goal = datastore.get(key)
    }

    def listByMotorista(motoristaId) {
        datastore.execute {
            select all
            from log
            where idMotorista == motoristaId
            sort desc by dateCreated
        }
    }

    def delete(id) {
        Key key = KeyFactory.createKey("motorista", Long.parseLong(id))
        key.delete()
    }

    def update(id, nome, celular, grupos) {
        def e = get(id)
        e.nome = nome
        e.celular = celular
        e.groups = prepareGroups(groups)
        e.save()
    }

    def add(idMotorista, nomeMotorista, operacao) {
        def e = new Entity("log")
        e.idMotorista = idMotorista
        e.nomeMotorista = nomeMotorista
        e.operacao = operacao
        e.userEmail = user.nickname
        e.dateCreated = (new Clock()).getDateTime()
        e.save()
    }
}
