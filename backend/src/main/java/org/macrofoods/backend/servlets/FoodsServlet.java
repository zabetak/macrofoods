package org.macrofoods.backend.servlets;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.macrofoods.backend.entities.jpa.Food;
import org.macrofoods.backend.entities.jpa.FoodDescription;
import org.macrofoods.backend.entities.jpa.FoodDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription;
import org.macrofoods.backend.entities.jpa.FoodGroupDescription_;
import org.macrofoods.backend.entities.jpa.FoodGroup_;
import org.macrofoods.backend.entities.jpa.Food_;
import org.macrofoods.backend.entities.jpa.Nutrient;
import org.macrofoods.backend.entities.jpa.NutrientData;
import org.macrofoods.backend.entities.jpa.NutrientData_;
import org.macrofoods.backend.entities.jpa.Nutrient_;

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
		response.setContentType("text/html");
		EntityManager em = newEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = builder.createTupleQuery();
		Root<Food> food = q.from(Food.class);
		Join<Food, FoodDescription> fDescription = food.join(Food_.descriptions);
		Join<Food, FoodGroup> fGroup = food.join(Food_.fdGroup);
		Join<FoodGroup, FoodGroupDescription> fgDescription = fGroup.join(FoodGroup_.descriptions);
		Join<Food, NutrientData> fData = food.join(Food_.data, JoinType.LEFT);
		Join<NutrientData, Nutrient> nutrient = fData.join(NutrientData_.nutrient);
		q.where(builder.and(
				builder.like(builder.upper(fDescription.get(FoodDescription_.longDesc).as(String.class)),
						likeExpPattern),
				builder.or(builder.equal(nutrient.get(Nutrient_.tagName), "PROCNT"),
						builder.equal(nutrient.get(Nutrient_.tagName), "CHOCDF"),
						builder.equal(nutrient.get("tagName"), "FAT"),
						builder.equal(nutrient.get(Nutrient_.tagName), "ENERC_KCAL"))));
		q.groupBy(food.get("id"), fDescription.get(FoodDescription_.longDesc),
				fgDescription.get(FoodGroupDescription_.shortDesc));
		q.multiselect(food.get(Food_.id), fDescription.get(FoodDescription_.longDesc),
				fgDescription.get(FoodGroupDescription_.shortDesc),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "ENERC_KCAL"),
								fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "PROCNT"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "CHOCDF"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)),
				builder.sum(builder.selectCase()
						.when(builder.equal(nutrient.get(Nutrient_.tagName), "FAT"), fData.get(NutrientData_.amount))
						.otherwise(builder.literal(0)).as(BigDecimal.class)));

		TypedQuery<Tuple> query = em.createQuery(q);
		response.getWriter().append("<table border=\"1\">");
		response.getWriter().append("<tr>");
		response.getWriter().append("<td>ID</td>");
		response.getWriter().append("<td>DESCRIPTION</td>");
		response.getWriter().append("<td>CATEGORY</td>");
		response.getWriter().append("<td>KCAL</td>");
		response.getWriter().append("<td>PROTEIN</td>");
		response.getWriter().append("<td>CARBS</td>");
		response.getWriter().append("<td>FAT</td>");
		response.getWriter().append("</tr>");
		for (Tuple t : query.getResultList()) {
			response.getWriter().append("<tr>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(0)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(1)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(2)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(3)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(4)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(5)));
			response.getWriter().append("</td>");
			response.getWriter().append("<td>");
			response.getWriter().append(String.valueOf(t.get(6)));
			response.getWriter().append("</td>");
			response.getWriter().append("</tr>");
		}
		response.getWriter().append("</table>");
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
