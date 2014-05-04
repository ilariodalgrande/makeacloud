package com.makeacoffee.cloud.logic;

import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.makeacoffee.cloud.dispatcherhub.DispatcherHubFactory;
import com.makeacoffee.cloud.dispatcherhub.DispatcherHubManager;
import com.makeacoffee.cloud.entity.Device;
import com.makeacoffee.cloud.entity.House;
import com.makeacoffee.cloud.entity.State;
import com.makeacoffee.cloud.entity.User;

/**
 * Classe per la gestione centralizzata delle funzionalita' dell'applicazione.
 * 
 * <p>Funzionalita' esposte:
 * <ul>
 * <li>restituzione dettagli utente a partire dalla suo identificativo univoco</li>
 * <li>invocazione di un cambio di stato</li>
 * </ul>
 * 
 * <p>TODO: da valutare se dividere in più classi specializzate o di fare singleton.
 * 
 * @author massimo.cattai
 * @version 0.2
 */
public class MakeACoffeeService {
	/* ***********************************************************************/
	public MakeACoffeeService() {
		
	}
	
	/* ***********************************************************************/
	/**
	 * Ottenimento dei dettagli di un utente.
	 * 
	 * @param userId identificativo univoco dell'utente.
	 * @return
	 */
	public static User getUserById(Long userId) {
		// Oggetto contenente i dettagli dell'utente caricato da DS
		User user = null;
		
		// Caricamento da DS dei dettagli dell'utente
		//TODO: utilizzo del DAO
		user = ofy().load().type(User.class).id(userId).now();
		
		// Restituzione dell'utente caricato
		return user;
	}
	
	/**
	 * Ottenimento dei dettagli di un utente.
	 * 
	 * @param userId identificativo univoco dell'utente.
	 * @return
	 */
	public static User getUserByEmail(String userEmail) {
		// Oggetto contenente i dettagli dell'utente caricato da DS
		User user = null;
		
		// Decodifica della email dell'utente
		String email = null;
		try {
			email = URLDecoder.decode(userEmail, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Utente da caricare: " + email);
		
		// Caricamento da DS dei dettagli dell'utente
		//TODO: utilizzo del DAO
		user = ofy().load().type(User.class).filter("email", email).first().now();
		System.out.println("Utente caricato: " + user);

		
		// Restituzione dell'utente caricato
		return user;
	}
	
	/**
	 * Ottenimento dei dettagli di una casa.
	 * 
	 * @param houseId identificativo univoco della casa.
	 * @return
	 */
	public static House getHouse(Long houseId) {
		// Oggetto contenente i dettagli della casa caricata da DS
		House house = null;
		
		// Caricamento da DS dei dettagli della casa
		//TODO: utilizzo del DAO
		house = ofy().load().type(House.class).id(houseId).now();
		
		// Restituzione della casa caricata
		return house;
	}
	
	/**
	 * Ottenimento dei dettagli di un device.
	 * 
	 * @param deviceId identificativo univoco del device.
	 * @return
	 */
	public static Device getDevice(Long deviceId) {
		// Oggetto contenente i dettagli del device caricato da DS
		Device device = null;
		
		// Caricamento da DS dei dettagli del device
		//TODO: utilizzo del DAO
		device = ofy().load().type(Device.class).id(deviceId).now();
		
		// Restituzione del device caricato
		return device;
	}
	
	/**
	 * Esecuzione di un evento di un device.
	 * 
	 * @return l'oggetto rappresentante il nuovo stato.
	 */
	public static State fireEvent(Long deviceId, String eventId) {
		// Per cercare di aggiustare il bug del mancato caricamento
		// del corretto device, ripulisco la cache
		ofy().clear();
		// Caricamento del device
		Device device = getDevice(deviceId);
		// Caricamento della casa relativa al device
		House house = getHouse(device.getHouseId());
		// Ottenimento del corretto Dispatcher Hub Manager
		DispatcherHubManager dhm = DispatcherHubFactory.getInstance().createManager(house.getDispatcherHub());
		// Lancio dell'evento verso il dispatcher hub
		dhm.fireEvent(deviceId, eventId);
		
		// Esecuzione dell'evento sullo stato corrente del device
		// NB: lo stato su DS cambia automaticamente
		State ns = device.fireEvent(eventId);
		
		// Restituzione del nuovo stato
		return ns;
	}
	
//	/**
//	 * Esecuzione di un evento di un device.
//	 * 
//	 * @return l'oggetto rappresentante il device nello nuovo stato.
//	 */
//	public Device fireEvent(Device device, String eventId) {
//		// Per cercare di aggiustare il bug del mancato caricamento
//		// del corretto device, ripulisco la cache
//		ofy().clear();
//		// Provo con le transazioni --> vedere piu' avanti
//		
//		System.out.print("Device ["+device.getCurrentState().getId()+
//				"]."+eventId+"=");
//		
//		// Esecuzione dell'evento sull'stato corrente del device
//		// NB: lo stato su DS cambia automaticamente
//		String newState = device.fireEvent(eventId);
//		
//		System.out.println(newState + " - " + device.getCurrentState().getId());
//		
//		// TODO: lancio dell'evento verso il dispatcher hub
//		
//		// Restituzione del device aggiornato
//		return device;
//	}
	/* ***********************************************************************/
	/* ***********************************************************************/
	/* ***********************************************************************/
	/* ***********************************************************************/
}
