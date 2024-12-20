package io.github.dmgtechlabs.users;

class User {
  private String username = null;
  private String email = null;
  private String password = null;
  private String FirtName = null;
  private String LastName = null;
  private String role = null;

  public User() {
  }

  public User(String username, String email, String password, String FirtName, String LastName) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.FirtName = FirtName;
    this.LastName = LastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirtName() {
    return FirtName;
  }

  public void setFirtName(String FirtName) {
    this.FirtName = FirtName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String LastName) {
    this.LastName = LastName;
  }

  public void login() {
    boolean user = true;
    String user_role_from_db = "SysManager";
    if (user) {
      this.role = user_role_from_db;
      this.username = "John Doe";
      System.out.println("User is logged in");
    } else {
      System.out.println("User is not logged in");
    }
  }
}
