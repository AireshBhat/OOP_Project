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
  HotelStayDetails hsd;
  public void runHotelStayDetailsFrame() {
    hsd = new HotelStayDetails();
  }
  // HotelListFrame hlf;
  // public void runHotelListFrame() {
    // hlf = new HotelListFrame();
    // hlf.init();
  // }
  HotelList hl;
  public void runHotelListFrame() {
    hl = new HotelList();
  }
}
