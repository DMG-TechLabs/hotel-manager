package io.github.dmgtechlabs.models;
import io.github.dmgtechlabs.db.Dao;
import io.github.kdesp73.databridge.connections.AvailableConnections;
import io.github.kdesp73.databridge.connections.PostgresConnection;
import io.github.kdesp73.databridge.helpers.SQLogger;
import java.sql.SQLException;
import io.github.dmgtechlabs.Utils;
import io.github.dmgtechlabs.exceptions.PermissionDenied;
import java.sql.ResultSet;
import io.github.kdesp73.databridge.helpers.Adapter;
import java.util.List;
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

    public int getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUser_type() {
        return user_type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser_type(UserType user_type) {
        this.user_type = user_type;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }
    
    

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
        conn.callProcedure("insert_new_user", user_id, hotel_id, username, password, user_type.value);
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
   
   public static List<User> selectWithUsernamePassword(String username, String password) throws Exception {
        assert (username != null);
        assert (!username.isBlank());
        assert (!"".equals(username));
        
        assert (password != null);
        assert (!password.isBlank());
        assert (!"".equals(password));
        
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.ORACLE.getConnection()) {
            ResultSet rs = conn.callFunction("select_user_with_username_password", username, password);
            return Adapter.load(rs, User.class);
        } catch (Exception e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select user failed", e);
            throw new Exception(e.getMessage());
//            return null;
        }
   }
   
   public static User login(String username, String password) throws IllegalArgumentException, Exception{
       List<User> users_list = User.selectWithUsernamePassword(username, password);
       if(users_list.size() == 1){
           return users_list.get(0);
       }else{
           throw new IllegalArgumentException("Username or Password error");
       }
   }
   
   public List<User> Admin_SelectAllUsers() throws PermissionDenied{
       if(this.user_type.value == User.UserType.ADMIN.value){
           try(PostgresConnection conn = (PostgresConnection) AvailableConnections.ORACLE.getConnection()) {
                ResultSet rs = conn.callFunction("select_user_with_username_password", username, password);
                return Adapter.load(rs, User.class);
            } catch (Exception e) {
                SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select user failed", e);
                return null;
            }
       }else{
           throw new PermissionDenied();
       }
   }
   
   
}
