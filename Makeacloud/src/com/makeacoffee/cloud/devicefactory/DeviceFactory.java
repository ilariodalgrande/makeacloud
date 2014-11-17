package com.makeacoffee.cloud.devicefactory;

import com.makeacoffee.cloud.entity.Device;

public class DeviceFactory {
	/** Istanza univoca della classe. */
	private static DeviceFactory uniqueInstance;
	
	private DeviceFactory() {
		// TODO: do something
	}
	
	public DeviceFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DeviceFactory();
		}
		
		return uniqueInstance;
	}
	
//	public Device createDevice(String typology) {
//		// TODO: gestire piu' tipologie, per ora solo Alicia
//		return AliciaFactory.createDevice();
//	}
}
