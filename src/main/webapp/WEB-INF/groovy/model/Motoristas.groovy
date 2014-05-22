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

    def countRanking() {
        datastore.execute {
            select count
            from motorista
            where pontos > 0
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

    def listByBonus() {
        datastore.execute {
            select all
            from motorista
            where bonus > 0
            sort desc by bonus
        }
    }

    def listByPontos() {
        datastore.execute {
            select all
            from motorista
            where pontos > 0
            sort desc by pontos
        }
    }

    def count() {
        datastore.execute {
            select count
            from motorista
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
        datastore.withTransaction(true) {
            def e = get(id)

            new Dashboard().subtract("total_pontos", e.pontos)
            new Dashboard().subtract("total_bonus", e.bonus)
            new Dashboard().subtract("total_${e.categoria}", 1)

            Key key = KeyFactory.createKey("motorista", Long.parseLong(id))
            key.delete()
        }
    }

    def update(id, nome, celular, groups) {
        datastore.withTransaction(true) {
            def e = get(id)

            def operacao = "Atualizou "
            if (nome != e.nome) operacao += "NOME de '$e.nome' para '$nome;' "
            if (celular != e.celular) operacao += "CELULAR de $e.celular para $celular; "
            new Logs().add(id, nome, "ALTERAÇÃO" , operacao)

            e.nome = nome
            e.celular = celular
            e.groups = prepareGroups(groups)
            e.lastUpdated = (new Clock()).getDateTime()
            e.save()
        }
    }

    def add(nome, celular, groups) {
        datastore.withTransaction(true) {
            def e = new Entity("motorista")
            e.nome = nome
            e.celular = celular
            e.pontos = 0
            e.bonus = 0
            e.curti = 0
            e.naoCurti = 0
            e.categoria = "bronze"
            e.groups = prepareGroups(groups)
            e.dateCreated = (new Clock()).getDateTime()
            e.lastUpdated = e.dateCreated
            e.userEmail = user.nickname
            e.save()

            new Dashboard().add("total_bronze", 1)
        }
    }

    def addPontos(id, pontos, regiao, pontosEstados, pontosEntregas) {
        def e = get(id)

        def pontosRegiao = pontos
        pontos += pontosEstados + pontosEntregas
        
        def pagarBonus = null
        int bonus = 100
        def novaCategoria = "bronze"
        int novaPontuacao = (int)e.pontos + pontos
        
        if (novaPontuacao >= 50000 && novaPontuacao< 150000) {
            novaCategoria = "prata"
            bonus = 150
        } else if (novaPontuacao >= 150000) {
            novaCategoria = "ouro"
            bonus = 200
        }

        datastore.withTransaction(true) {
            def operacao = "Adicionou $pontos pontos ($regiao); "
            if (pontosEstados > 0 || pontosEntregas > 0) {
              operacao = "Adicionou $pontos pontos, sendo $pontosRegiao de $regiao; "
              if (pontosEstados > 0) {
                operacao += "+$pontosEstados pela entrega em 2 ou mais Estados;"
              }
              if (pontosEntregas > 0) {
                operacao += "+$pontosEntregas por ter 5 ou mais Entregas;"
              }
            }
            new Logs().add(id, e.nome, "PONTOS", operacao)
            new Dashboard().add("total_pontos", pontos)

            if ((int)(novaPontuacao / 10000) > (int)(e.pontos / 10000)) {
                e.bonus += bonus

                operacao = 'Pagou bônus de R$' + bonus
                new Logs().add(id, e.nome, "BONUS", operacao)

                pagarBonus = bonus

                new Dashboard().add("total_bonus", bonus)
            }

            if (novaCategoria != e.categoria) {
                new Dashboard().subtract("total_${e.categoria}", 1)
                new Dashboard().add("total_$novaCategoria", 1)
                e.categoria = novaCategoria
            } else {
                novaCategoria = null
            }

            e.pontos = novaPontuacao
            e.lastUpdated = (new Clock()).getDateTime()
            e.save()
        }
        return [pagarBonus:pagarBonus, novaCategoria:novaCategoria]
    }

    def addCurti(id, texto) {
        def e = get(id)
        datastore.withTransaction(true) {
            new Logs().add(id, e.nome, "CURTI", texto)
            //new Dashboard().add("total_pontos", pontos)
            e.curti =  (e.curti!=null?e.curti:0) + 1
            e.lastUpdated = (new Clock()).getDateTime()
            e.save()
        }
    }

    def addSMS(id, texto) {
        def e = get(id)
        datastore.withTransaction(true) {
            new Logs().add(id, e.nome, "SMS", texto)
            //new Dashboard().add("total_pontos", pontos)
            e.lastSMS = texto
            e.lastUpdated = (new Clock()).getDateTime()
            e.save()
        }
    }

    def addNaoCurti(id, texto) {
        def e = get(id)
        datastore.withTransaction(true) {
            new Logs().add(id, e.nome, "NÃO CURTI", texto)
            //new Dashboard().add("total_pontos", pontos)
            e.naoCurti = (e.naoCurti!=null?e.naoCurti:0) + 1
            e.lastUpdated = (new Clock()).getDateTime()
            e.save()
        }
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
