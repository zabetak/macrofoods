package org.macrofoods.backend.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Test Servlet to verify that the EntityManager and the transaction are
 * present.
 */
public class TestTransactionServlet extends EMServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9219614838968874316L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestTransactionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager em = newEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
		response.getWriter().append("Transaction OK");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
