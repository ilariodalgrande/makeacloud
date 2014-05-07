package com.makeacoffee.cloud.dispatcherhub.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.makeacoffee.cloud.entity.State;
import com.makeacoffee.cloud.logic.MakeACoffeeService;

@Path("/dh")
public class DispatcherHubApi {
	@GET
	@Path("/{device}/{event}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public String notifyEvent(@PathParam("device") Long deviceId, @PathParam("event") String eventId) {
		System.out.println("Notifica dal dispatcherhub del risultato di un'azione");
		System.out.println("Device da aggiornare: " + deviceId);
		System.out.println("Evento invocato sul device: " + eventId);
		
		State toState = MakeACoffeeService.fireEvent(deviceId, eventId);
		
		return toState.getId();
	}
}
