package com.makeacoffee.cloud.dao;

import com.google.appengine.api.datastore.Entity;


/**
 * Interfaccia di base dei DAO.
 * 
 * <p>Vengono definite le firme dei metodi CRUD.
 * 
 * @author massimo.cattai
 * @version 0.1
 */
public interface Dao {
	public Object load(String encodedKey);
	public String save(Object object);
	public void delete(String encodedKey);
	public String getEncodedKey(Object object);
}
