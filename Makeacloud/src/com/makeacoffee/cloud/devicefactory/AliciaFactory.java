package com.makeacoffee.cloud.devicefactory;

import com.makeacoffee.cloud.entity.Device;

public class AliciaFactory {
	// TODO: generalizzarla

	/**
	 * Creazione di un nuovo dispositivo di tipologia "<code>Alicia</code>".
	 * 
	 * @param houseId seriale univoco della casa dove e' installato il
	 *                dispositivo.
	 * @param devideId TODO
	 * @param name nome del dispositivo attuale.
	 * @return una nuova istanza della classe <code>Device</code> rappresentante
	 *         il dispositivo appena creato.
	 */
	public static Device createDevice(String houseId, String deviceId, String name) {
		// Creazione e salvataggio del nuovo dispositivo
		Device alicia = new Device(houseId, deviceId, name, "Spento");

		// Creazione e salvataggio delle transazioni
		alicia.addTransaction("Spento", "Accendi", "Acceso");
		alicia.addTransaction("Acceso", "Spegni", "Spento");
		alicia.addTransaction("Acceso", "Fai il caffe'", "Caffe' pronto");
		alicia.addTransaction("Caffe' pronto", "Spegni", "Spento");

		return alicia;
	}
}
