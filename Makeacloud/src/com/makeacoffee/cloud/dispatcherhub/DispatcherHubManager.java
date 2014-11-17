package com.makeacoffee.cloud.dispatcherhub;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class DispatcherHubManager {
	/** <p>String di connessione al Dispatcher Hub.</p> */
	private String url;
	
	/* ***********************************************************************/
	protected DispatcherHubManager(String url) {
		this.url = url;
	}

	/* ***********************************************************************/
	/**
	 * <p>Invoca un evento su di un device.</p>
	 * 
	 * <p>Il dispatcher hub riconosce i dispositivi con lo stesso identificativo del DS.</p>
	 * 
	 * @param deviceId identificativo del device.
	 * @param eventId identificativo dell'evento.
	 */
	public void fireEvent(String deviceId, String eventId) {
		// TODO: da implementare
		// Connessione verso il DH
		connect();
		// Invocazione dell'evento
		// TODO: si utilizzera' RESTful quindi una cosa del tipo: https://<ind>/device/<id device>/event/<id evento>
		System.out.println("invocazione verso il dispatcher hub [" + url
				+ "] dell'evento [" + eventId
				+ "] sul dispositivo [" + deviceId
				+ "]");
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
//		URI baseURI = UriBuilder.fromUri("http://localhost:18080/HelloWebServices").build(); // Test purpose
		URI baseURI = UriBuilder.fromUri("http://makeacoffee.no-ip.org:8080/dispatcherhub").build();
		WebResource service = client.resource(baseURI);
		System.out.println(service.path("dh").path("ed").path(deviceId.toString()).path(eventId).accept(MediaType.APPLICATION_JSON).get(String.class));
	}

	/* ***********************************************************************/
	private void connect() {
		// TODO: si connettera' al dispatcher hub
	}
}
