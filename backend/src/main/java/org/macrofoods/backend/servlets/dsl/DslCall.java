package org.macrofoods.backend.servlets.dsl;

public class DslCall implements DslNode {
	private DslOperator operator;
	private DslNode leftOperand;
	private DslNode rightOperand;

	public DslCall(DslOperator op, DslNode left, DslNode right) {
		this.operator = op;
		this.leftOperand = left;
		this.rightOperand = right;
	}

	@Override
	public String toString() {
		String left = leftOperand != null ? leftOperand.toString() : "";
		String right = rightOperand != null ? rightOperand.toString() : "";
		return operator.toString() + "(" + left + "," + right + ")";
	}

}
