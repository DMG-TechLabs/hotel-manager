package io.github.dmgtechlabs.models;

class Users {
  private String username;
  private String email;
  private String password;
  private String FirtName;
  private String LastName;

  public Users(String username, String email, String password, String FirtName, String LastName) {
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
    if (user) {
      this.username = "John Doe";
      System.out.println("User is logged in");
    } else {
      System.out.println("User is not logged in");
    }
  }
}
