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
import java.util.ArrayList;
import java.util.List;
//import io.github.dmgtechlabs.users.UserRepository;



public class User implements Dao {
    public enum UserType {
        ADMIN(1),
        GUEST(2),
        EMPLOYEE(3),
        MANAGER(4);

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
            throw new IllegalArgumentException("Invalid value for User Type: " + value);
        }
    }
    
    private int id;
    private String username;
    private String password;
    private int type;
    private int accountHotelFk;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getType() {
        return type;
    }

    public int getAccountHotelFk() {
        return accountHotelFk;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setAccountHotelFk(int accountHotelFk) {
        this.accountHotelFk = accountHotelFk;
    }
    
    

  public User(int id, String username, String password, int type, int account_hotel_fk) {
      this.accountHotelFk = account_hotel_fk;
      this.password = password;
      this.id = id;
      this.type = type;
      this.username = username;
    
  }
  
  
  
  @Override
  public boolean insert() {
      try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure("insert_account",username, password, type,  accountHotelFk);
      } catch (SQLException e) {
        SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert User failed", e);
        return false;
      }
      return true;
  };
  
  @Override
  public boolean insertWithException() throws Exception {
      try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure("insert_account",username, password, type,  accountHotelFk);
      } catch (SQLException e) {
        SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Insert User failed", e);
        throw new Exception(e.getMessage());
//        return false;
      }
      return true;
  }

  @Override
  public boolean update(Object... values){
      final int expectedParams = 3;
		if(values.length != expectedParams)
            throw new IllegalArgumentException(String.format("Invalid number of values (%s). Expected %d", values.length, expectedParams));

        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            conn.callProcedure("update_account", this.id, values[0], values[1], values[2]);
        } catch (SQLException e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Update hotel failed", e);
            return false;
        }
        return true;
  };

  @Override
   public boolean delete(){
       try (PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
        conn.callProcedure("delete_user_with_id", this.id);
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
        
        try(PostgresConnection conn = (PostgresConnection) AvailableConnections.POSTGRES.getConnection()) {
            ResultSet rs = conn.callFunction("select_user_with_username_password", username, password);
//            while(rs.next()){
//                System.out.println(rs.getString(0));
//            }
            List<User> users = new ArrayList<User>();
            while(rs.next()){
//                int id, String username, String password, int type, int account_hotel_fk
                System.out.println("aaaa");
                users.add(new User(rs.getInt("id"),rs.getString("username"),rs.getString("password"),rs.getInt("type"),rs.getInt("account_hotel_fk")));
            }
//            return Adapter.load(rs, User.class);
            return users;
        } catch (Exception e) {
            SQLogger.getLogger().log(SQLogger.LogLevel.ERRO, "Select user failed", e);
            throw new Exception(e.getMessage());
//            return null;
        }
   }
   
   public static User login(String username, String password) throws IllegalArgumentException, Exception{
       List<User> users_list = User.selectWithUsernamePassword(username, password);
       System.out.println(users_list);
       if(users_list.size() == 1){
           return users_list.get(0);
       }else{
           throw new IllegalArgumentException("Username or Password error");
       }
   }
   
   public List<User> Admin_SelectAllUsers() throws PermissionDenied{
       if( this.type == User.UserType.ADMIN.value){
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
