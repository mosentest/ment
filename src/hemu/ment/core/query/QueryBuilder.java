package hemu.ment.core.query;

public class QueryBuilder {
	
	public static final String AND = " AND ";
	
	public static final String OR = " OR ";
	
	public static final String EQ = " = ";

	private StringBuilder query;
	
	private boolean firstCondition;
	
	private boolean whereAdded;
	
	public QueryBuilder() {
		query = new StringBuilder();
	}
	
	public void setSelect(String select) {
		query.insert(0, select);
	}
	
	public void addJoin(String join) {
		query.append(" " + join + " ");
	}
	
	public void andCondition(String condition) {
		addCondition(AND, condition);
	}
	
	public void orCondition(String condition) {
		addCondition(OR, condition);
	}
	
	public void addCondition(String type, String condition) {
		if (!whereAdded) {
			whereAdded = true;
			query.append(" WHERE ");
			firstCondition = true;
		}
		if (firstCondition) {
			query.append(condition);
			firstCondition = false;
		} else {
			query.append(type);
			query.append(condition);
		}
	}
	
	public void leftParenthes() {
		query.append("(");
		firstCondition = true;
	}
	
	public void rightParenthes() {
		query.append(")");
	}
	
	public String getQuery() {
		return query.toString();
	}
	
}
