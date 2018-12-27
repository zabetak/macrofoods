package org.macrofoods.backend.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.services.RecipeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Servlet implementation class GetRecipeServlet
 */
public final class GetRecipeServlet extends EMServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public GetRecipeServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getPathInfo().substring(1));
		EntityManager em = newEntityManager();
		try {
			RecipeService service = new RecipeService(em);
			RecipeDTO recipe = service.findRecipe(id);
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			response.getWriter().append(writer.writeValueAsString(recipe));
		} finally {
			if (em != null)
				em.close();
		}
	}

}
