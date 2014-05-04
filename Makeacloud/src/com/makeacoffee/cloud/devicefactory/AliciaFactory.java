package com.makeacoffee.cloud.devicefactory;

import com.makeacoffee.cloud.entity.Device;

public class AliciaFactory {
	// TODO: generalizzarla

	public static Device createDevice(Long houseId, String name) {
		// Creazione e salvataggio del nuovo dispositivo
		Device alicia = new Device(houseId, name, "Spento");

		// Creazione e salvataggio delle transazioni
		alicia.addTransaction("Spento", "Accendi", "Acceso");
		alicia.addTransaction("Acceso", "Spegni", "Spento");
		alicia.addTransaction("Acceso", "Fai il caffe'", "Caffe' pronto");
		alicia.addTransaction("Caffe' pronto", "Spegni", "Spento");

		return alicia;
	}
}
