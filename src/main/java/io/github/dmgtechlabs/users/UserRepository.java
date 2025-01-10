package io.github.dmgtechlabs.users;

import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.Adapter;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// import io.github.dmgtechlabs.users.User;

public class UserRepository implements Dao {

  public UserRepository() {
  }

  private boolean dbProcedures(Object value, String procedure_name) {
    assert (procedure_name != null);
    assert (!procedure_name.isBlank());

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

  private List<User> select(String procedure_name, Object... values) {
    assert (procedure_name != null);
    assert (!procedure_name.isBlank());

    try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
      ResultSet rs = conn.callFunction(procedure_name, values);
      return Adapter.load(rs, User.class);
    } catch (Exception e) {
      SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select " + procedure_name + " failed", e);
      return null;
    }

  }

  public User login(String username, String password) {
    return select("login_user", username, password).get(0);
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
