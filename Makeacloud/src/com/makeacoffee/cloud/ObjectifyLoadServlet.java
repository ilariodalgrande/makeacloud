package com.makeacoffee.cloud;

import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObjectifyLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Devinizione degli id
		// NB: saranno passati dal client invocante o ricavati dal UserService
		String macchiId = "macchi";
		
		// Lettura degli oggetti di prova
//		MakeACoffeUser macchi = ofy().load().type(MakeACoffeUser.class).id(macchiId).now();
//
//		// Output risultati
//		resp.setContentType("text/plain");
//		PrintWriter pw = resp.getWriter();
//		pw.println("Hello, GAE Objectify");
//		pw.println("Bentornato, " + macchi.getId());
//		pw.println("Hai attualmente registrate sul tuo profilo le seguenti case:");
//		for (House house : macchi.getHouses()) {
//			pw.println(" - " + house.getId());
//			pw.println("   In questa casa sono presenti i seguenti dispositivi:");
//			for (Device device : house.getDevices()) {
//				pw.println("    - " + device.getId() + " [" + device.getCurrentState().getId() + "]");
//				pw.println("      Eventi ammessi:");
//				for (Event event : device.getCurrentState().getEvents()) {
//					pw.println("       - <<" + event.getId() + ">> --> [" + event.getNextState() + "]");
//				}
//			}
//		}
	}
}
