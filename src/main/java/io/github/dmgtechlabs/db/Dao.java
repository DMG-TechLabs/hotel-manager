package io.github.dmgtechlabs.db;

public interface Dao {

	default boolean insert() {
		return false;
	};

	public boolean update(Object... values);

	public boolean delete();

	default boolean insert(Object... values) {
		return false;
	};
}
