package org.macrofoods.backend.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.dto.UserDTO;
import org.macrofoods.backend.services.AuthenticationService;
import org.macrofoods.backend.services.TokenService;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

/**
 * Servlet implementation class AuthenticationServlet
 */
public final class TokenServlet extends EMServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TokenServlet() {
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		BufferedReader br = null;
		EntityManager em = null;
		try {
			br = request.getReader();
			ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			ObjectReader reader = mapper.readerFor(UserDTO.class);
			UserDTO user = reader.readValue(br);
			AuthenticationService service = new AuthenticationService(newEntityManager());
			boolean isAdmin = service.isAdmin(user.getUsername(), user.getPassword());
			if (!isAdmin)
				response.sendError(401);
			else
				response.getWriter().write("{" + "\"token\":\"" + TokenService.INSTANCE.create() + "\","
						+ "\"expiresIn\":\"" + TokenService.INSTANCE.expiresIn() + "\"" + "}");
		} finally {
			if (em != null)
				em.close();
			if (br != null)
				br.close();
		}
	}

}
