package makeacoffee.cloud.makeacloud;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class MakeacloudServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		// We have one entity group per Guestbook with all Greetings residing
		// in the same entity group as the Guestbook to which they belong.
		// This lets us run a transactional ancestor query to retrieve all
		// Greetings for a given Guestbook. However, the write rate to each
		// Guestbook should be limited to ~1/second.
		String guestbookName = req.getParameter("guestbookName");
		// Generazione di una chiave univoca per un oggetto sul Datastore
		// In questo caso si sta creando una chiave nella root (non si sta specificando nessun parent) di tipo "Guestbook" e nome dato dalla variabile "guestbookName"
		Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
		
		// Informazione da registrare nel Datastore 
		String content = req.getParameter("content");
		// Data dell'evento
		Date date = new Date();

		// Creazione di una nuova entity da registrare nel Datastore
		// In questo caso si sta creando una nuova entity di tipo "Greeting" specificando come padre l'oggetto "guestbookKey"
		Entity greeting = new Entity("Greeting", guestbookKey);
		greeting.setProperty("user", user);
		greeting.setProperty("date", date);
		greeting.setProperty("content", content);
		
		// Ottenimento dell'oggetto per accedere al Datastore
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		// Inserimento della nuova entity nel Datastore
		datastore.put(greeting);
		
		// Reindirizzamento alla jsp
		resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
	}
}
