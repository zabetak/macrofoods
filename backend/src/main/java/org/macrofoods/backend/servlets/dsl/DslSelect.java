package org.macrofoods.backend.servlets.dsl;

import java.util.List;

public class DslSelect implements DslNode {
	private final DslIdentifier name;
	private final List<DslSelect> subselects;
	private final DslCall filter;

	public DslSelect(DslIdentifier name, List<DslSelect> subselect, DslCall filter) {
		super();
		this.name = name;
		this.subselects = subselect;
		this.filter = filter;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(filter == null ? "" : "[" + filter.toString() + "]");
		if (!subselects.isEmpty())
			sb.append("(");
		for (int i = 0; i < subselects.size(); i++) {
			if (i > 0) {
				sb.append(',');
				sb.append(' ');
			}
			sb.append(subselects.get(i));
		}
		if (!subselects.isEmpty())
			sb.append(")");
		return sb.toString();
	}
}
