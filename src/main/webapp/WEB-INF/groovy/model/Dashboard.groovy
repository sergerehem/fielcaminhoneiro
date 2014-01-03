package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class Dashboard {

    def get() {
        def e = datastore.execute {
            select single
            from dashboard
        }

        if (e == null) {

            datastore.withTransaction {
                e = new Entity("dashboard")
                e.total_pontos = 0
                e.total_bonus = 0
                e.total_bronze = 0
                e.total_prata = 0
                e.total_ouro = 0
                return e.save()
            }
        }

        return e
    }

    def list() {
        datastore.execute {
            select single
            from dashboard
            sort asc by name
        }
    }

    /*
    def delete(id) {
        Key key = KeyFactory.createKey("dashboard", dashboardID)
        key.delete()
    }*/

    def update(chave, valor) {
        datastore.withTransaction {
            def e = get()
            e."$chave" = valor
            e.save()
        }
    }

    def add(chave, valor) {
        println "vou add $chave $valor"
        datastore.withTransaction {
            def e = get()
            e."$chave" += valor
            e.save()
        }
    }

    def subtract(chave, valor) {
        println "vou sub $chave $valor"
        datastore.withTransaction {
            def e = get()
            e."$chave"  -= valor
            e.save()
        }
    }
}
