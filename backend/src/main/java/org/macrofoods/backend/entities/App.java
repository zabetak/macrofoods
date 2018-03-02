package org.macrofoods.backend.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.macrofoods.backend.entities.jpa.Food;

public final class App {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Food f = new Food();
		em.persist(f);
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
