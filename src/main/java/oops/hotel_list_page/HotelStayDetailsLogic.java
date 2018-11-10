package oops.hotel_list_page;

import oops.project.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Class that implements all the login in the frame
public class HotelStayDetailsLogic {
  User user = new User();
  public void setUserDetails(String checkInDate, String checkOutDate, int duration, int noPpl) {
    try {
      FileReader fr = new FileReader(HotelStayDetails.FILE);
      CSVReader csr = new CSVReader(fr);
      String[] recordReader;
      FileWriter fw = new FileWriter("./users.csv");
      CSVWriter csw = new CSVWriter(fw);
      String[] recordWriter;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
