package io.github.dmgtechlabs.users;

import io.github.kdesp73.databridge.*;
import io.github.kdesp73.databridge.connections.SQLiteConnection;
import io.github.kdesp73.databridge.helpers.SQLogger;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.DatabaseConnection;

public class UserRepository {

  private SQLiteConnection connection = new SQLiteConnection();

  public UserRepository(String db_url, String db_username, String db_password) {
    try (DatabaseConnection conn = AvailableConnection.SQLITE.getConnection()) {
      ResultSet rs = conn.executeQuery("SELECT * FROM people");

      SQLogger.logResultSet(rs);

      rs.close();
    } catch (SQLExcetpion e) {
      e.printStackTrace();
    }
    this.connection.connect(db_url, db_username, db_password);
  }
}
