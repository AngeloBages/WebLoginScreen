package com.loginscreen.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {
	
	private static EntityManagerFactory EMF;

	public static EntityManager getEntityManager() {
		
		if(EMF == null) {
			EMF = Persistence.createEntityManagerFactory("login-screen_hibernate");
		}
		
		return EMF.createEntityManager();
	}
}
