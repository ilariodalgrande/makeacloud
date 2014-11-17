package com.makeacoffee.cloud;

import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.makeacoffee.cloud.entity.Device;
import com.makeacoffee.cloud.entity.House;
import com.makeacoffee.cloud.entity.User;

public class MakeACoffeeLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Ottenimento dei dati dell'utente
		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		System.out.println(user);
		
		String userId = "macchi";
		
		// Creazione del nuovo utente
		//TODO: creare dao
		User macchi = ofy().load().type(User.class).id(userId).now();

		// Pagina
		resp.setContentType("text/plain");
		PrintWriter pw = resp.getWriter();
		pw.println("Hello, Make a Coffee");
		pw.println("Hello, GAE Objectify");
		pw.println("Bentornato, " + macchi.getId());
		pw.println("Hai attualmente registrate sul tuo profilo le seguenti case:");
//		for (House house : macchi.getHouses()) {
//			pw.println(" - " + house.getId());
//			pw.println("   In questa casa sono presenti i seguenti dispositivi:");
//			for (Device device : house.getDevices()) {
//				pw.println("    - " + device.getId() + " [" + device.getCurrentState() + "]");
//				pw.println("      Eventi ammessi stato corrente:");
//				for (Event event : device.getCurrentState().getEvents()) {
//					pw.println("       - " + event);
//				}
//			}
//		}
	}
	

}
