import com.google.appengine.api.datastore.*
import util.*

def e = new Entity("twilio_callback")
e << params
e.dateCreated = (new Clock()).getDateTime()
e.save()