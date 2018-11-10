package oops.project;

import oops.hotel_list_page.*;

public class FrameControl {
  public void runLoginScreen(User user) {
    Login lg = new Login();
    lg.init(user);
  }
  public void runNewUserSignup(User user) {
    NewUserSignup nus = new NewUserSignup();
    nus.init(user);
  }
  public void runHotelStayDetailsFrame(String username) {
    HotelStayDetails hsd = new HotelStayDetails(username);
  }
}
