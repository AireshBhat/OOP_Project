package oops.hotel_list_page;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import oops.project.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class HotelListLogic {
  User user = new User();

  public HotelListLogic () {}

  protected ArrayList<String[]> hotelList() {
    ArrayList<String[]> listOfHotels = new ArrayList<String[]>();
    // String listBeforeConv[] = user.getLocation().split(",").trim();
    // List<String> hotelLocation = new ArrayList<String> jbbb
    String[] hotelLocation = user.getLocation().split("[\\s*,\\s*\\s+]");
    String[] hotelAddress;
    String[] innerHotelAddress;
    boolean contains = false;
    try {
      CSVReader csr = new CSVReader(new FileReader(HotelList.HOTEL_FILE));
      FileWriter fw = new FileWriter("./src/main/java/static_files/checkHotelList.csv", false);
      CSVWriter csw = new CSVWriter(fw);
      String[] recordReaderEach;
      int addressLength = 0;
      int innerAddressLength = 0;
      while ((recordReaderEach = csr.readNext()) != null) {
        contains = false;
        hotelAddress = recordReaderEach[1].split("\\s*,\\s*");
        addressLength = hotelAddress.length;
        for (int i = 0; i < addressLength; i++){
          innerHotelAddress = hotelAddress[i].split("\\s+");
          innerAddressLength = innerHotelAddress.length;
          for (int j = 0; j < innerAddressLength; j ++) {
            if (containsLocation(innerHotelAddress[j], hotelLocation)) {
              contains = true;
            }
          }
        }
        if (contains) {
          listOfHotels.add(recordReaderEach);
        }
      }
      csw.writeAll(listOfHotels);
      csw.close();
      fw.close();
      csr.close();
    } catch (IOException ee) {
      ee.printStackTrace();
    }
    return listOfHotels;
  }

  protected boolean containsLocation(String hotelLocation, String[] location) {
    return Arrays.asList(location).contains(hotelLocation);
  };

}
