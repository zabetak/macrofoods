package org.macrofoods.backend.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.ImageDTO;
import org.macrofoods.backend.services.ImageService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Servlet implementation class ImagesServlet
 */
public final class ImagesServlet extends EMServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ImagesServlet() {
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
			ImageService service = new ImageService(em);
			ImageDTO img = service.findBy(id);
			ObjectMapper mapper = new ObjectMapper();
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			response.getWriter().append(writer.writeValueAsString(img));
		} finally {
			if (em != null)
				em.close();
		}
	}

}
