package org.macrofoods.backend.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.RecipeDTO;
import org.macrofoods.backend.services.RecipeService;
import org.macrofoods.backend.servlets.dsl.DslQuery;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Servlet implementation class TopRecipesServlet
 */
public final class TopRecipesServlet extends EMServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TopRecipesServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		String path = request.getParameter("path");
		EntityManager em = newEntityManager();
		try {
			RecipeService service = new RecipeService(em);
			DslQuery query = new DslQuery() {

				public DslQuery subQuery(String field) {
					return this;
				}

				public boolean satisfies(String field, Object value) {
					return true;
				}

				public boolean matches(String field) {
					return true;
				}
			};
			if (path != null) {
				// Simple1 parser = new Simple1(new ByteArrayInputStream(path.getBytes()));
				// query = parser.Input();
			}
			List<RecipeDTO> recipes = service.topRecipes(query);
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			response.getWriter().append(writer.writeValueAsString(recipes));
			// } catch (ParseException e) {
			// throw new RuntimeException("", e);
		} finally {
			em.close();
		}
	}

}
