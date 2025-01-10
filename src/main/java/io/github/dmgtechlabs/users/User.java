package io.github.dmgtechlabs.users;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.SQLException;
//import io.github.dmgtechlabs.users.UserRepository;

public class User implements Dao {
    public enum UserType {
        WORKER(1),
        ADMIN(2);

        private final int value;

        UserType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static User.UserType fromValue(int value) {
            for (User.UserType type : User.UserType.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid value for Hotel Amenity: " + value);
        }
    }
    
    private int user_id;
    private String username;
    private String password;
    private UserType user_type;
    private int hotel_id;

  public User(int user_id, String username, String password, UserType user_type, int hotel_id) {
      this.hotel_id = hotel_id;
      this.password = password;
      this.user_id = user_id;
      this.user_type = user_type;
      this.username = username;
    
  }
  
  @Override
  public boolean insert(){
      try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure("insert_new_user", this.user_id, this.hotel_id, this.username, this.password, this.user_type.value);
      } catch (SQLException e) {
        SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert User failed", e);
        return false;
      }
      return true;
  };

  @Override
  public boolean update(Object... values){
      final int expectedParams = 5;
		if(values.length != expectedParams)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected %d", values.length, expectedParams));

        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.ORACLE.getConnection()) {
            conn.callProcedure("update_user", Utils.appendFront(this.hotel_id, values));
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update hotel failed", e);
            return false;
        }
        return true;
  };

  @Override
   public boolean delete(){
       try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure("delete_user_with_id", this.user_id);
      } catch (SQLException e) {
        SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Delete User failed", e);
        return false;
      }
      return true;
   };


//  static User login(String username, String password) {
//    // User user = UserRepository().login(username, password);
//    //
//
//    boolean user = true;
////    String user_role_from_db = "SysManager";
//    if (user) {
//      System.out.println("User is logged in");
//    } else {
//      System.out.println("User is not logged in");
//    }
//  }
}
