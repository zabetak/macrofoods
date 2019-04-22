package org.macrofoods.backend.servlets.dsl;

public interface DslQuery {
	boolean matches(String field);

	boolean satisfies(String field, Object value);

	DslQuery subQuery(String field);
}
