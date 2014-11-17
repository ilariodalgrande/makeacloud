package com.makeacoffee.cloud.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.makeacoffee.cloud.entity.House;
import com.makeacoffee.cloud.entity.State;
import com.makeacoffee.cloud.logic.MakeACoffeeService;

/**
 * API principale per il progetto "Make a Coffee"
 * 
 * @author massimo.cattai
 * @version 0.1
 */
@Api(name = "makeacoffee",
	version = "v1")
public class Houses {

	@ApiMethod(name = "houses.get",
			path = "houses/{id}",
			httpMethod = HttpMethod.GET)
	public House getHouse(@Named("id") String id) {
		System.out.println("Casa da caricare: " + id);

		House h = MakeACoffeeService.getHouse(id);

		System.out.println("Casa caricata: " + h);

		return h;
	}
}
