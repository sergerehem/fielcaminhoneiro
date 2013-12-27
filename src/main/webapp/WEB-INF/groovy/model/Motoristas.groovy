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

  def update(id, nome, celular, pontos, groups) {
		//datastore.withTransaction {
		  def e = get(id)  

			def operacao = "Atualizou "
			if (nome != e.nome) operacao += "NOME de '$e.nome' para '$nome;' "
			if (celular != e.celular) operacao += "CELULAR de $e.celular para $celular; "
			if (pontos != e.celular) operacao += "PONTOS de $e.pontos para $pontos; "
			new Logs().add(id, nome, operacao)

		  e.nome = nome
		  e.celular = celular 
			e.pontos = pontos  
		  e.groups = prepareGroups(groups)
		  e.save()	
		//}
  }

	def add(nome, celular, pontos, groups) {
    def e = new Entity("motorista")
    e.nome = nome
    e.celular = celular
		e.pontos = pontos
    e.groups = prepareGroups(groups)
    e.dateCreated = (new Clock()).getDateTime()
		e.userEmail = user.nickname
    e.save()	
	}

	def addPontos(id, pontos) {
		def e = new Entity("motorista")
		e.pontos += pontos
		e.save()
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
