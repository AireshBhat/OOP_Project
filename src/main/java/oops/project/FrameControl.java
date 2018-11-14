package oops.project;

import oops.hotel_list_page.*;

public class FrameControl {
    static public void runLoginScreen() {
        Login lg = new Login();
        lg.init();
    }

    static public void runNewUserSignup() {
        NewUserSignup nus = new NewUserSignup();
        nus.init();
    }

    static public void runHotelStayDetailsFrame() {
        HotelStayDetails hsd = new HotelStayDetails();
    }

    static public void runHotelListFrame() {
        HotelList hl = new HotelList();
    }

    static public void runUserFrame() {
        UserFrame uf = new UserFrame();
    }
}
