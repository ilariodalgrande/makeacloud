package com.makeacoffee.cloud.entity;

import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.util.Hashtable;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Device {
	@Id
	@Getter @Setter
	// Chiave interna al DS
	private Long id;
	@Getter @Setter
	private String name;// TODO: serial number? per ora "alicia-1234567890"
	@Getter @Setter
	private String description;

	@Getter
	/**
	 * Identificativo univoco della casa dove si trova attualmente il dispositivo.
	 */
	private Long houseId;

	private String currentStateId;

	private Map<String, State> states;

	/* ******************************************************************/
	public Device(Long houseId, String name, String initialStateId) {
		this.houseId = houseId;
		this.name = name;
		this.description = new String();
		this.states = new Hashtable<String, State>();
		this.currentStateId = addState(initialStateId, false).getId();
		// al salvataggio il DS generera' l'id
		save();
	}
	
	public Device(House house, String name, String initialStateId) {
		this(house.getId(), name, initialStateId);
	}
	
	/* ******************************************************************/
	public State getState(String stateId) {
		return states.get(stateId);
	}
	
	public State getCurrentState() {
		return getState(currentStateId);
	}
	
	/**
	 * Aggiunge una transazione di stato all'interno della Finite State Machine
	 * corrente.
	 * <p>
	 * Da notare che vengono passati semplicemente gli identificativi
	 * degli stati e degli eventi e non gli oggetti veri e propri, questo
	 * perche' all'interno del metodo vengono istanziati gli oggetti
	 * delle classi corrispondenti e memorizzati all'interno della FSM.
	 * <p>
	 * In aggiunta si specifica se lo stato di arrivo e' o meno un "landing
	 * state", ovvero rappresentante uno stato in cui l'end device e' in
	 * attesa di ricevere nuovi input o e' in corso un evento complesso.
	 * 
	 * @param from identificativo dello stato di partenza.
	 * @param event identificativo dell'evento.
	 * @param to identificativo dello stato di arrivo.
	 * @param isLanding flag per identificare lo stato di arrivo come "landing
	 *                  state" o meno.
	 */
	public void addTransaction(String fromId, String eventId, String toId) {
		// stato di partenza
		State fromS = getState(fromId);
		if (fromS == null) {
			fromS = addState(fromId, false);
		}

		// stato di arrivo
		State toS = getState(toId);
		if (toS == null) {
			toS = addState(toId, false);
		}
		
		// stato intermedio
		State intS = addState(fromId+";"+toId, true);
		
		// stato di errore
		State errS = getState("Error");
		if (errS == null) {
			errS = addState("Error", false);
		}
		
		// evento dallo stato di partenza a quello intermedio
		Event from2IntE = new Event(eventId, intS.getId());
		
		// evento dallo stato intermedio a quello di arrivo
		Event int2ToE = new Event("OK", toS.getId());

		// evento dallo stato intermedio allo di errore
		Event int2ErrE = new Event("KO", errS.getId());

		// Aggiunta eventi agli stati
		// from
		fromS.addEvent(from2IntE);
		// int
		intS.addEvent(int2ToE);
		intS.addEvent(int2ErrE);
		
		// Salvattaggio della transazione appena creata
		save();
	}
	
	public State fireEvent(String eventId) {
		// Ottenimento dello stato corrente
		//TODO: controllo presenza dello stato cercato
		State currS = getState(this.currentStateId);
		// Lancio dell'evento sullo stato corrente
		String nextSId = currS.fireEvent(eventId);
		// Aggiornamento dello stato corrente
		this.currentStateId = nextSId;
		// Salvataggio del device modificato
		save();
		// Restituzione del nuovo stato
		return getCurrentState();
	}

	/* ******************************************************************/
	/**
	 * 
	 * @param state
	 * @param isLanding flag per identificare lo stato di arrivo come "landing
	 *                  state" o meno.
	 * @return una nuova istanza della classe State.
	 */
	private State addState(String stateId, boolean intermediate) {
		// Creazione nuovo stato
		State state = new State(stateId, intermediate);
		// Inserimento dello stato nel device
		states.put(state.getId(), state);

		return state;
	}
	
	/* Objectify ******************************************************************/
	/**
	 * Salva l'evento corrente.
	 */
	public void save() {
		ofy().save().entity(this).now();
	}

	/* ******************************************************************/
	@Override
	public String toString() {
		return "["+id+"]";
	}
}
