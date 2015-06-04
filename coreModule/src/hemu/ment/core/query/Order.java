package hemu.ment.core.query;

/**
 * Created by muu on 2015/6/1.
 */
public class Order {

	private String name;

	private String column;

	private boolean ascending;

	public Order(String name, String column, boolean ascending) {
		this.name = name;
		this.column = column;
		this.ascending = ascending;
	}

	public Order() {
	}

	@Override
	public String toString() {
		return column + " " + (ascending ? "ASC" : "DESC");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}
}
