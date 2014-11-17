package com.makeacoffee.cloud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makeacoffee.cloud.devicefactory.AliciaFactory;
import com.makeacoffee.cloud.devicefactory.BoilerFactory;
import com.makeacoffee.cloud.entity.Device;
import com.makeacoffee.cloud.entity.House;
import com.makeacoffee.cloud.entity.User;

public class MakeACoffeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Creazione del nuovo utente
		User user = new User("diego.braga86@gmail.com");

		// Creazione di una nuova casa
		House h1 = new House("Casa di Milano");
		House h2 = new House("Casa di Verona");
		
		// Associazione della nuova casa all'utente e viceversa
		user.addHouse(h1);
		user.addHouse(h2);
		h1.addOwner(user);
		h2.addOwner(user);

		// Creazione di nuovi dispositivi
		Device d1 = AliciaFactory.createDevice(h1.getId(), "Alicia");
		Device d2 = BoilerFactory.createDevice(h2.getId(), "Boiler");
		
		// Associazione dell'Alicia alla casa
		h1.addDevice(d1);
		h2.addDevice(d2);

		// Pagina
		resp.setContentType("text/plain");
		PrintWriter pw = resp.getWriter();
		pw.println("Hello, Make a Coffee");
		pw.println("User:   " + user);
		pw.println("House:  " + h1);
		pw.println("Device: " + d1);
		pw.println("House:  " + h2);
		pw.println("Device: " + d2);
	}
	

}
