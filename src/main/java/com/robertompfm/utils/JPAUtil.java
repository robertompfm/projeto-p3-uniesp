package com.robertompfm.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(Constants.DBETO_UNIT);
		}
		
		return factory;
	}
	
	public static void closeFactory() {
		factory.close();
	}
}
