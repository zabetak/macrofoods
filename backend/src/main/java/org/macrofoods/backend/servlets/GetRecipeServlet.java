package org.macrofoods.backend.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.services.RecipeService;
import org.macrofoods.backend.services.TokenService;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		try {
			TokenService.INSTANCE.verify(token);
		} catch (JWTVerificationException ve) {
			response.sendError(401);
			throw ve;
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
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
			response.getWriter().write("{\"operation\":\"saveRecipe\", \"status\":\"success\", \"recipeid\":\"" + id
					+ "\", \"recipetitle\":\"" + recipe.getTitle() + "\"}");
		} finally {
			if (em != null)
				em.close();
			if (br != null)
				br.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
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

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getHeader("Authorization");
		try {
			TokenService.INSTANCE.verify(token);
		} catch (JWTVerificationException ve) {
			resp.sendError(401);
			throw ve;
		}
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Integer id = Integer.parseInt(req.getPathInfo().substring(1));
		BufferedReader br = null;
		EntityManager em = null;
		try {
			br = req.getReader();
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			ObjectReader reader = mapper.readerFor(RecipeDTO.class);
			RecipeDTO recipe = reader.readValue(br);
			em = newEntityManager();
			RecipeService service = new RecipeService(em);
			service.updateRecipe(recipe);
			resp.getWriter().write("{\"operation\":\"updateRecipe\", \"status\":\"success\", \"recipeid\":\"" + id
					+ "\", \"recipetitle\":\"" + recipe.getTitle() + "\"}");
		} finally {
			if (em != null)
				em.close();
			if (br != null)
				br.close();
		}
	}

}
