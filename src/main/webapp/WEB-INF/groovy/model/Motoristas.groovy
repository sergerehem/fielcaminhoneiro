package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class Motoristas {

    def get(id) {
        def longId = Long.parseLong(id)
        Key key = KeyFactory.createKey("motorista", longId)
        def goal = datastore.get(key)
    }

    def list() {
        datastore.execute {
            select all
            from motorista
            sort asc by nome
        }
    }


    def listRanking() {
        datastore.execute {
            select all
            from motorista
            where pontos > 0
            sort desc by pontos
        }
    }

    def listBySearch(search) {
        search.search {
            select all
            from motorista
            where nome =~ search
        }
    }

    def listByGroup(group) {
        datastore.execute {
            select all
            from motorista
            where groups == group
            sort asc by nome
        }
    }

    def countByGroup(group) {
        datastore.execute {
            select count
            from motorista
            where groups == group
        }
    }

    def delete(id) {
        Key key = KeyFactory.createKey("motorista", Long.parseLong(id))
        key.delete()
    }

    def update(id, nome, celular, groups) {
        //datastore.withTransaction {
        def e = get(id)

        def operacao = "Atualizou "
        if (nome != e.nome) operacao += "NOME de '$e.nome' para '$nome;' "
        if (celular != e.celular) operacao += "CELULAR de $e.celular para $celular; "
        if (pontos != e.pontos) operacao += "PONTOS de $e.pontos para $pontos; "
        new Logs().add(id, nome, operacao)

        e.nome = nome
        e.celular = celular
        e.groups = prepareGroups(groups)
        e.lastUpdated = (new Clock()).getDateTime()
        e.save()
        //}
    }

    def add(nome, celular, groups) {
        def e = new Entity("motorista")
        e.nome = nome
        e.celular = celular
        e.pontos = 0
        e.bonus = 0
        e.categoria = "bronze"
        e.groups = prepareGroups(groups)
        e.dateCreated = (new Clock()).getDateTime()
        e.lastUpdated = e.dateCreated
        e.userEmail = user.nickname
        e.save()
    }

    def addPontos(id, pontos, regiao) {
        //datastore.withTransaction {
        def e = get(id)

        def operacao = "Adicionou $pontos pontos ($regiao); "
        new Logs().add(id, e.nome, operacao)

        def novaPontuacao = (int)e.pontos + pontos
        def bonus = 0
        def pagarBonus = null
        def novaCategoria = null

        if (novaPontuacao < 50000) {
            novaCategoria = "bronze"
            bonus = 100
        } else if (novaPontuacao >= 50000 && novaPontuacao< 150000) {
            novaCategoria = "prata"
            bonus = 150
        } else if (novaPontuacao >= 150000) {
            novaCategoria = "ouro"
            bonus = 200
        }

        if ((int)(novaPontuacao / 10000) > (int)(e.pontos / 10000)) {
            e.bonus += bonus

            operacao = 'Pagou bônus de R$' + bonus
            new Logs().add(id, e.nome, operacao)

            pagarBonus = bonus
        }

        if (novaCategoria != e.categoria) {
            e.categoria = novaCategoria
        } else {
            novaCategoria = null
        }

        e.pontos = novaPontuacao
        e.lastUpdated = (new Clock()).getDateTime()
        e.save()
        return [pagarBonus:pagarBonus, novaCategoria:novaCategoria]
    }

    private def prepareGroups(groups) {
        if (groups == null) {
            return null
        } else if (groups instanceof String) {
            return [groups]
        } else if (groups) {
            return groups.toList()
        }
    }
}
