package oops.project;

import oops.hotel_list_page.*;

public class Main {
    public static void main(String args[]) {
      User user = null;
      // FrameControl fm = new FrameControl();
      // fm.runLoginScreen(user);
      Login lg = new Login();
      NewUserSignup nus = new NewUserSignup();
      nus.init();
      // new HotelStayDetails();
    }
}
