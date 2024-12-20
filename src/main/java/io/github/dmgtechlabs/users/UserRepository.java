package io.github.dmgtechlabs.users;

import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
// import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
// import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.List;
// import io.github.dmgtechlabs.users.User;

public class UserRepository implements Dao {

  public UserRepository(String db_url, String db_username, String db_password) {
  }

  private boolean dbProcedures(Object value, String procedure_name) {
    if (value instanceof User) {
      User user = (User) value;
      try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure(procedure_name, user.getUsername(), user.getPassword(), user.getEmail(), user.getFirtName(),
            user.getLastName());
      } catch (SQLException e) {
        SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert User failed", e);
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean insert(Object... values) {
    boolean result = false;
    for (Object value : values) {
      result = dbProcedures(value, "insert_user");
      if (!result) {
        return false;
      }
    }

    return true;
  }

  @Override
  public boolean update(Object... values) {
    boolean result = false;
    for (Object value : values) {
      result = dbProcedures(value, "update_user");
      if (!result) {
        return false;
      }
    }
    return false;
  }

  @Override
  public boolean delete(Object... values) {
    boolean result = false;
    for (Object value : values) {
      result = dbProcedures(value, "delete_user");
      if (!result) {
        return false;
      }
    }
    return false;
  }
}
