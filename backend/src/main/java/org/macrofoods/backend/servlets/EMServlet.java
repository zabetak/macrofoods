package org.macrofoods.backend.servlets;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;

/**
 * A {@link HttpServlet} providing a convenient method to obtain an
 * {@link EntityManager}.
 * 
 */
abstract class EMServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8898348616099574981L;

	final EntityManager newEntityManager() {
		EntityManagerFactory emf = (EntityManagerFactory) getServletContext()
				.getAttribute(ContextListener.EM_FACTORY_ATTR);
		return emf.createEntityManager();
	}
}
