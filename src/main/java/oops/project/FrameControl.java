package oops.project;

public class FrameControl {
  public void runLoginScreen(User user) {
    Login lg = new Login();
    lg.init(user);
  }
  public void runNewUserSignup(User user) {
    NewUserSignup nus = new NewUserSignup();
    nus.init(user);
  }
}
