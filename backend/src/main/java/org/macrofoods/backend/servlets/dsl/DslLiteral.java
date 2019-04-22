package org.macrofoods.backend.servlets.dsl;

public class DslLiteral implements DslNode {
	private final Object value;

	public DslLiteral(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value != null ? value.toString() : "null";
	}
}
