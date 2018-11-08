package oops.project;

public class Main {
    public static void main(String args[]) {
      User user;
      NewUserSignup nus = new NewUserSignup;
      public void runNewUserScreen() {
          nus.init();
      }
      Login lg = new Login();
      // NewUserSignup nus = new NewUserSignup();
      user = lg.init();
      // nus.init();
    }
}
