package io.github.dmgtechlabs.db;

public interface Dao {

	default boolean insert() {
		return false;
	};

	public boolean update(Object... values);

	default boolean delete() {
		return false;
	};

	default boolean delete(Object... values) {
		return false;
	};

	default boolean insert(Object... values) {
		return false;
	};
}
