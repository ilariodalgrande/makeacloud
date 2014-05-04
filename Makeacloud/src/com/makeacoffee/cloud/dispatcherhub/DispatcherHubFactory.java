package com.makeacoffee.cloud.dispatcherhub;

public class DispatcherHubFactory {
	private static DispatcherHubFactory uniqueInstance;
	
	/* ***********************************************************************/
	private DispatcherHubFactory() {
		// do something
	}
	
	/* ***********************************************************************/
	public static DispatcherHubFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DispatcherHubFactory();
		}
		return uniqueInstance;
	}
	
	/* ***********************************************************************/
	public DispatcherHubManager createManager(String url) {
		// TODO: dovremo utilizzare internamente parametri di conf per identificare il dispatcher hub
		return new DispatcherHubManager(url);
	}
}
