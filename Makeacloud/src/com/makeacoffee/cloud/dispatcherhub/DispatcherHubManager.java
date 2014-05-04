package com.makeacoffee.cloud.dispatcherhub;

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
	public void fireEvent(Long deviceId, String eventId) {
		// TODO: da implementare
		// Connessione verso il DH
		connect();
		// Invocazione dell'evento
		// TODO: si utilizzera' RESTful quindi una cosa del tipo: https://<ind>/device/<id device>/event/<id evento>
		System.out.println("invocazione verso il dispatcher hub [" + url
				+ "] dell'evento [" + eventId
				+ "] sul dispositivo [" + deviceId
				+ "]");
	}

	/* ***********************************************************************/
	private void connect() {
		// TODO: si connettera' al dispatcher hub
	}
}
