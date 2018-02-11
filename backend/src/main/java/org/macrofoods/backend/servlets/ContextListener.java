package org.macrofoods.backend.servlets;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class ContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent evt) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
		evt.getServletContext().setAttribute(EMServlet.EM_FACTORY_ATTR, emf);
	}

	public void contextDestroyed(ServletContextEvent evt) {
		EntityManagerFactory emf = (EntityManagerFactory) evt.getServletContext()
				.getAttribute(EMServlet.EM_FACTORY_ATTR);
		if (emf != null)
			emf.close();
		// TODO Else log appropriate
	}
}
