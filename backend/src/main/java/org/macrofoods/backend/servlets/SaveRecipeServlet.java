package org.macrofoods.backend.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.services.RecipeService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 */
public final class SaveRecipeServlet extends EMServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public SaveRecipeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader br = null;
		EntityManager em = null;

		try {
			br = request.getReader();
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			ObjectReader reader = mapper.readerFor(RecipeDTO.class);
			RecipeDTO recipe = reader.readValue(br);
			em = newEntityManager();
			RecipeService service = new RecipeService(em);
			Integer id = service.saveRecipe(recipe);
			response.getWriter()
					.write("{\"operation\":\"saveRecipe\", \"status\":\"success\", \"recipeid\":\"" + id + "\"}");
		} finally {
			if (em != null)
				em.close();
			if (br != null)
				br.close();
		}
	}

}
