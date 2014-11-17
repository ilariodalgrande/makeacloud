package com.makeacoffee.cloud.dao;

import java.util.Hashtable;
import java.util.Map;

/**
 * Factory per l'ottenimento dei DAO specifici per ogni Entity salvata su Datastore.
 * 
 * <p>Implementata utilizzando il pattern <code>Singleton</code>.
 * 
 * @author massimo.cattai
 * @version 0.1
 */
public class DaoFactory {
	// Istanza univoca della classe
	private static DaoFactory uniqueInstance;
	
	private Map<Class<?>, Class<?>> daos;
	
	/**
	 * Costruttore di default.
	 */
	private DaoFactory() {
		// Creazione della mappa tra le Entity e i corrispettivi DAO
		daos = new Hashtable<Class<?>, Class<?>>();
	}
	
	/**
	 * Restituisce l'istanza univoca della classe.
	 * @return l'istanza univoca.
	 */
	public static DaoFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new DaoFactory();
		}
		
		return uniqueInstance;
	}
	
	/**
	 * Creazione di un DAO per una particolare classe di Entity.
	 * 
	 * @param entityClass
	 * @return
	 */
	public Dao createDao(Class<?> entityClass) {
		// DAO da creare
		Dao dao = null;
		
		// Ottenimento della classe del DAO riferito all'Entity
		Class<?> daoClass = daos.get(entityClass);
		
		// Utilizzo della reflection per la creazione di un'istanza del DAO
		try {
			dao = (Dao) daoClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dao;
	}
	
	/**
	 * Metodo interno alla classe utilizzato per registrare le implementazioni specifiche
	 * della classe astratta <code>Dao</code>
	 * 
	 * @param entityClass la tipologia dell'Entity da registrare.
	 * @param daoClass la tipologia del DAO associato all'Entity.
	 */
	public void register(Class<?> entityClass, Class<?> daoClass) {
		daos.put(entityClass, daoClass);
	}

	public void register(Class<?> entityClass) {
		// Nome della classe dell'Entity
		String entityClassName = entityClass.getName();
		
		// Creazione del nome della classe del DAO legato all'Entity
		// NB: per convenzione la classe si chiamerà come la classe
		//     dell'Entity con il post-fisso "DAO"
		String daoClassName = entityClassName + "DAO";
		
		// Ottenimento della classe relativa al DAO
		try {
			Class<?> daoClass = Class.forName(daoClassName);
			
			// Invocazione del metodo esteso di registrazione del DAO
			register(entityClass, daoClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void register(String entityClassName) {
		try {
			Class<?> entityClass = Class.forName(entityClassName);
			
			register(entityClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
