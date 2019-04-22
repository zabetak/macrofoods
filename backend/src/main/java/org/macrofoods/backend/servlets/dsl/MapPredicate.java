package org.macrofoods.backend.servlets.dsl;

import java.util.Map;
import java.util.function.Predicate;

public final class MapPredicate implements Predicate<Map<String, Object>> {

	private final String id;
	private final Object constant;

	public MapPredicate(String id, Object constant) {
		this.id = id;
		this.constant = constant;
	}

	public boolean test(Map<String, Object> arg0) {
		Object v = arg0.get(id);
		return v == null ? false : v.equals(constant);
	}

}
