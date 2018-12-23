package org.macrofoods.backend.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.entities.jpa.LangCode;
import org.macrofoods.backend.services.FoodsService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A Servlet to list all foods in the database along with their associated
 * macros matching the provided description.
 */
public class FoodsServlet extends EMServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9219614838968874316L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FoodsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String longdesc = request.getParameter("description");
		String likeExpPattern = "%" + longdesc.toUpperCase().replace(" ", "%") + "%";
		response.setContentType("application/json");
		EntityManager em = newEntityManager();
		FoodsService service = new FoodsService(em, LangCode.EN);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter()
				.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.findFoods(likeExpPattern)));
		em.close();
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
