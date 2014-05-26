package model

import com.google.appengine.api.datastore.*
import com.google.appengine.api.datastore.KeyFactory
import com.google.appengine.api.datastore.Key
import groovyx.gaelyk.GaelykBindings
import util.*

@GaelykBindings
public class Groups {
  
    def get(id) {
        def longId = Long.parseLong(id)
        Key key = KeyFactory.createKey("group", longId)
        def goal = datastore.get(key)
    }
  
    def list() {
        datastore.execute {
            select all
            from group
            sort asc by name
        }
    }
    
    def delete(id) {
        Key key = KeyFactory.createKey("group", Long.parseLong(id))
        key.delete()
    }

    def add(name, description) {
        def e = new Entity("group")
        e.name = name
        e.description = description
        e.dateCreated = (new Clock()).getDateTime()
        e.lastUpdated = e.dateCreated
        e.userEmail = user.nickname
        e.save()
    }

    def update(id, description) {
        def e = get(id)
        e.description = description
        e.lastUpdated = (new Clock()).getDateTime()
        e.save()
    }

}
