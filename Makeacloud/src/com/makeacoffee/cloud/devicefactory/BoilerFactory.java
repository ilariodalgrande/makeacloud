package com.makeacoffee.cloud.devicefactory;

import com.makeacoffee.cloud.entity.Device;

public class BoilerFactory {
	// TODO: generalizzarla

	public static Device createDevice(String houseId, String name) {
		// Creazione e salvataggio del nuovo dispositivo
		Device device = new Device(houseId, null, name, "Spento");

		// Creazione e salvataggio delle transazioni
		device.addTransaction("Spento", "Accendi", "Acceso");
		device.addTransaction("Acceso", "Spegni", "Spento");

		return device;
	}
}
