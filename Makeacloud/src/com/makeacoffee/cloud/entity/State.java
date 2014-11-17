package com.makeacoffee.cloud.entity;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class State {
	@Getter @Setter
	private String id;
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	/**
	 * Identifica se lo stato corrente e' uno stato intermedio, e quindi
	 * generato automaticamente.
	 * <p>Su questa tipologia di stati l'end device non puo' lanciare eventi
	 * ma solo il dispatcher hub.
	 */
	private boolean intermediate;

	@Setter
	private Map<String, Event> events;
	
	/* ******************************************************************/
	public State(String id, boolean intermediate) {
		this.id = id;
		this.intermediate = intermediate;
		this.events = new Hashtable<String, Event>();
	}
	
	/* ******************************************************************/
	public Collection<Event> getEvents() {
		return events.values();
	}
	
	public void addEvent(Event event) {
		events.put(event.getId(), event);
	}
		
	public String fireEvent(String eventId) {
		// Ottenimento dell'evento in base all'id
		Event e = events.get(eventId);
		
		// TODO: Controllo presenza effettiva dell'evento da lanciare
		// Esecuzione dell'evento e restituzione prossimo stato
		String nextState = e.fire();
		
		return nextState;
	}

	/* ******************************************************************/
	@Override
	public String toString() {
		return "["+id+"]";
	}
}
