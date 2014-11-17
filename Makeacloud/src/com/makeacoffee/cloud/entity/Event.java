package com.makeacoffee.cloud.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Event {
	@Getter
	private String id;

	private String toState;
	
	@Getter @Setter
	private boolean schedulable;
	
	/* ******************************************************************/
	public Event(String id, String toState) {
		this.id = id;
		this.toState = toState;
		this.schedulable = false; //TODO: per ora gli eventi non sono schedulabili
	}

	/* ******************************************************************/
	public String fire() {
		return toState;
	}

	/* ******************************************************************/
	@Override
	public String toString() {
		return "("+id+") --> ["+toState+"]";
	}
}
