package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class Pontos {
    def pontos = [
        "Nordeste/ Bahia e Sergipe":500,
        "Nordeste / Alagoas e Paraíba":1000,
        "Nordeste/ Ceará, Pernambuco e Rio Grande do Norte":1500,
        "Nordeste / Maranhão e Piauí":2000,
        "Norte / Todos os Estados":2000,
        "Sudeste / Todos os Estados":2500,
        "Centro Oeste / Todos os Estados":2500,
        "Sul/ Todos os Estados":3000,
    ]

    def list() {
        pontos
    }
}
