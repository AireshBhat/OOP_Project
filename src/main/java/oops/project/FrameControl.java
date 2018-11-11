package oops.project;

import oops.hotel_list_page.*;

public class FrameControl {
  public void runLoginScreen() {
    Login lg = new Login();
    lg.init();
  }
  public void runNewUserSignup() {
    NewUserSignup nus = new NewUserSignup();
    nus.init();
  }
  public void runHotelStayDetailsFrame() {
    HotelStayDetails hsd = new HotelStayDetails();
  }
  public void runHotelListFrame() {
    HotelList hl = new HotelList();
  }
}
