package io.github.dmgtechlabs.db;

public interface Dao {

	public boolean insert();
        
        default boolean insertWithException() throws Exception{return false;};

	public boolean update(Object... values);

	public boolean delete();
}
