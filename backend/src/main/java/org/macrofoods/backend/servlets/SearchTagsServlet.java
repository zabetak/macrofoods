package org.macrofoods.backend.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.entities.jpa.LangCode;
import org.macrofoods.backend.services.TagService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class SearchTagsServlet
 */
public final class SearchTagsServlet extends EMServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchTagsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		String tagName = request.getParameter("tagName");
		EntityManager em = newEntityManager();
		TagService service = new TagService(em, LangCode.US);
		ObjectMapper mapper = new ObjectMapper();
		response.getWriter()
				.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.findByName(tagName)));
		em.close();

	}

}
