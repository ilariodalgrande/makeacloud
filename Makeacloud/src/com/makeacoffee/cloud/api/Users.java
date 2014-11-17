package com.makeacoffee.cloud.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.makeacoffee.cloud.entity.State;
import com.makeacoffee.cloud.entity.User;
import com.makeacoffee.cloud.logic.MakeACoffeeService;

/**
 * API principale per il progetto "Make a Coffee"
 * 
 * @author massimo.cattai
 * @version 0.1
 */
@Api(name = "makeacoffee",
	version = "v1")
public class Users {

	@ApiMethod(name = "users.get",
			path = "users/{id}",
			httpMethod = HttpMethod.GET)
	public User getUserById(@Named("id") Long id) {
		System.out.println("Utente da caricare: " + id);

		User user = MakeACoffeeService.getUserById(id);

		System.out.println("Utente caricato: " + user);

		return user;
	}

	@ApiMethod(name = "login",
			path = "login/{email}",
			httpMethod = HttpMethod.GET)
	public User getUserByEmail(@Named("email") String email) {
		User user = MakeACoffeeService.getUserByEmail(email);
		return user;
	}
}
