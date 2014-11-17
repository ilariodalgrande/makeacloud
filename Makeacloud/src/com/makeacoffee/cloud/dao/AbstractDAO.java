package com.makeacoffee.cloud.dao;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public abstract class AbstractDAO implements Dao {
	@Override
	public abstract Object load(String encodedKey);

	@Override
	public abstract String save(Object object);

	@Override
	public abstract void delete(String encodedKey);

	@Override
	public abstract String getEncodedKey(Object object);

	protected abstract Entity object2Entity(Object object);

	protected abstract Object entity2Object(Entity entity);

	protected Entity loadEntity(String encodedKey) {
		// Datastore service
		DatastoreService datastore = getDatastoreService();
		// Chiave decodificata, utilizzata per la ricerca
		Key key = KeyFactory.stringToKey(encodedKey);
		// Creazione della corretta query
		Query q = new Query(key);
		// Esecuzione della query
		PreparedQuery pq = datastore.prepare(q);
		// Restituzione dell'entity caricata
		return pq.asSingleEntity();
	}
	
	protected void saveEntity(Entity entity) {
		// Datastore service
		DatastoreService datastore = getDatastoreService();
		// Salvataggio dell'entity nel Datastore
		datastore.put(entity);
	}
	
	private DatastoreService getDatastoreService() {
		return DatastoreServiceFactory.getDatastoreService();
	}
}
