package com.makeacoffee.cloud.api;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.makeacoffee.cloud.entity.Device;
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
public class Devices {
	@ApiMethod(name = "devices.get",
			path = "devices/{id}",
			httpMethod = HttpMethod.GET)
	public Device getDevice(@Named("id") String id) {
		System.out.println("Device da caricare: " + id);

		Device d = MakeACoffeeService.getDevice(id);

		System.out.println("Device caricato: " + d);

		return d;
	}

	@ApiMethod(name = "devices.fire",
			path = "devices/{device}/events/{event}",
			httpMethod = HttpMethod.POST)
	public State fireEvent(@Named("device") String deviceId,
			@Named("event") String eventId) {
		State toState = MakeACoffeeService.fireEvent(deviceId, eventId);

		return toState;
	}
}
