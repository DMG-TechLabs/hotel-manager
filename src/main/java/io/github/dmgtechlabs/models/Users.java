package io.github.dmgtechlabs.models;

class Users {
  private String name;

  public Users() {
    this.name = "";
  }

  public void login() {
    boolean user = true;
    if (user) {
      this.name = "John Doe";
      System.out.println("User is logged in");
    } else {
      System.out.println("User is not logged in");
    }
  }
}
