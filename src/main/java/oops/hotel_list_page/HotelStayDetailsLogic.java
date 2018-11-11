package oops.hotel_list_page;

import oops.project.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

// Class that implements all the login in the frame
public class HotelStayDetailsLogic {
  User user = new User();
  public void setUserDetails(String checkInDate, String checkOutDate, int duration, int noPpl) {
    try {
      FileReader fr = new FileReader(HotelStayDetails.FILE);
      CSVReader csr = new CSVReader(fr);
      String[] recordReader;
      FileWriter fw = new FileWriter(HotelStayDetails.FILE);
      CSVWriter csw = new CSVWriter(fw);
      String[] recordWriter;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // check whether the data input from the user is valid
  protected int validate(String checkIn, String checkOut, String rooms, String ppl) {
    try {
      int inDay = Integer.parseInt(checkIn.substring(0,1));
      int outDay = Integer.parseInt(checkOut.substring(0,1));
      int inMonth = Integer.parseInt(checkIn.substring(3, 4));
      int outMonth = Integer.parseInt(checkOut.substring(3, 4));
      Integer.parseInt(rooms);
      Integer.parseInt(ppl);
      if (inDay > 31 || inMonth > 12 || inDay < 1 || inMonth < 1) {
        return -1;
      }
      if (outDay > 31 || outMonth > 12 || outDay < 1 || outMonth < 1) {
        return -1;
      }
    } catch (NumberFormatException e) {
      e.printStackTrace();
      return -1;
    }
    return 1;
  }
  
  // Add the data to the csv
  // We first read all the data from the csv
  // Then remove the row of the user who has inputted the data
  // Then add the row of the user with the extra data inputted by the user
  // Then add all the contents into the csv again
  protected int addData(String location, String checkIn, String checkOut, String room, String ppl) {
    user.setCheckInDate(checkIn);
    user.setCheckOutDate(checkOut);
    user.setRoom(room);
    user.setPpl(ppl);
    user.setLocation(location);
    int locationOfUserInFile = 0;
    try {
      FileReader fr = new FileReader(HotelStayDetails.FILE);
      CSVReader csr = new CSVReader(fr);
      String[] recordReaderEach;
      List<String[]> recordReaderWhole = new ArrayList<String[]>();
      int counter = 0;
      int sizeOfRow;
      while ((recordReaderEach = csr.readNext()) != null) {
        sizeOfRow = recordReaderEach.length;
        recordReaderWhole.add(new String[] {
          recordReaderEach[0],
          recordReaderEach[1],
          recordReaderEach[2],
          recordReaderEach[3],
          recordReaderEach[4],
          5 < sizeOfRow - 1 ? recordReaderEach[5] : "",
          6 < sizeOfRow - 1 ? recordReaderEach[6] : "",
          7 < sizeOfRow - 1 ? recordReaderEach[7] : "",
          8 < sizeOfRow - 1 ? recordReaderEach[8] : "",
          9 < sizeOfRow - 1 ? recordReaderEach[9] : "",
        });
        if (recordReaderEach[0] == user.getUserName()) {
        } else {
          locationOfUserInFile = counter;
        }
        counter++;
      }
      recordReaderWhole.remove(locationOfUserInFile);
      recordReaderWhole.add(new String[] {
        user.getUserName(),
        user.getName(),
        user.getBirthday(),
        user.getPassword(),
        user.getAddress(),
        checkIn,
        checkOut,
        room,
        ppl,
        location,
      });
      FileWriter fw = new FileWriter(HotelStayDetails.FILE, false);
      CSVWriter csw = new CSVWriter(fw);
      csw.writeAll(recordReaderWhole);
      fr.close();
      csr.close();
      fw.close();
      csw.close();
    } catch (IOException ee) {
      ee.printStackTrace();
    }
    return 1;
  }
}
