package io.github.dmgtechlabs.db;

public interface Dao {

	public boolean insert();

	public boolean update(Object... values);

	public boolean delete();
}
