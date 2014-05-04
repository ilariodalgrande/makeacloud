package com.makeacoffee.cloud.entity;


import static com.makeacoffee.cloud.dao.OfyService.ofy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class House {
	@Id
	@Getter @Setter
	/**
	 * Identificativo univoco della casa.
	 * Generato automaticamente dal DS al salvataggio.
	 */
	private Long id;
	
	@Getter @Setter
	private String name;
	@Getter @Setter
	private String description;
	@Getter @Setter
	// Stringa di connessione verso il dispatcher hub
	// per ora fissa
	private String dispatcherHub = "localhost:8080";
	
	// Identificativi univici dei proprietari sul DS
	private List<Long> ownersId;
	
//	@Load
//	private List<Ref<Device>> devices;
	// Presumo che sia meglio memorizzare semplicemente l'id del device
	// in modo da forzarne il caricamento ogni volta per avere sempre
	// lo stato corrente
	@Getter
	private Map<String, Long> devicesId;
	
	/* ******************************************************************/
	public House(String name, String description) {
		this.name = name;
		this.description = description;
		this.ownersId = new ArrayList<Long>();
		this.devicesId = new Hashtable<String, Long>();
		// Salvataggio su DS ed assegnazione della chiave univoca
		save();
	}
	
	public House(String name) {
		this(name, null);
	}
	
	/* ******************************************************************/
	public void addOwner(Long ownerId) {
		ownersId.add(ownerId);
		// Salvataggio su DS
		save();
	}
	
	public void addOwner(User owner) {
		addOwner(owner.getId());
	}
	
	public void addDevice(String deviceName, Long deviceId) {
		devicesId.put(deviceName, deviceId);
		// Salvataggio su DS
		save();
	}
	
	public void addDevice(Device device) {
		addDevice(device.getName(), device.getId());
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
