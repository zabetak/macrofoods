package org.macrofoods.backend.servlets.dsl;

public class DslIdentifier implements DslNode {
	private final String id;

	public DslIdentifier(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id;
	}
}
