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
import com.googlecode.objectify.annotation.Index;


@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
	@Id
	@Getter
	// Identificativo univoco sul DS
	private Long id;
	
	@Index
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	// Per ora la mail dell'utente
	private String nickname;
	
	@Getter
	// Lista delle case possedute
	// E' una associazione tra l'id della casa su DS e il nome della casa
	private Map<String, String> houses;

	/* ******************************************************************/
	public User(String email) {
		this.email = email;
		//TODO: attualmente tutti sono amministratori
		this.houses = new Hashtable<String, String>();
		save();
	}
	
	/* ******************************************************************/
	public void addHouse(String houseName, String houseId) {
		houses.put(houseName, houseId);
		// Salvataggio dell'utente su DS
		save();
	}
	
	public void addHouse(House house) {
		addHouse(house.getName(), house.getId());
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
		return "["+email+"]";
	}
}
