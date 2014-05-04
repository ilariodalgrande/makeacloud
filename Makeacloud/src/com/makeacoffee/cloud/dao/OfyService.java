package com.makeacoffee.cloud.dao;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.makeacoffee.cloud.ObjectifySaveServlet.A;
import com.makeacoffee.cloud.entity.Device;
import com.makeacoffee.cloud.entity.Event;
import com.makeacoffee.cloud.entity.House;
import com.makeacoffee.cloud.entity.State;
import com.makeacoffee.cloud.entity.User;


public class OfyService {
	static {
		// Registrazione delle entity
		factory().register(User.class);
		factory().register(House.class);
		factory().register(Device.class);
		factory().register(A.class);
	}
	
	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}
	
	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}
}
