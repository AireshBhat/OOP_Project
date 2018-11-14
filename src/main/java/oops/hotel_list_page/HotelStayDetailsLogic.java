package oops.hotel_list_page;

import oops.project.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// Class that implements all the login in the frame
public class HotelStayDetailsLogic {
    private User user = new User();

    // check whether the data input from the user is valid
    boolean dateOrder(String checkIn, String checkOut) {
        Date date1;
        Date date2;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date1 = sdf.parse(checkIn);
            date2 = sdf.parse(checkOut);
            if (date1.before(date2)) {
                return false;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    boolean legitDate(String checkIn) {
        Date date1;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date1 = sdf.parse(checkIn);
            Date dCurrentDate = sdf.parse(sdf.format(new Date()));
            if (dCurrentDate.after(date1)) {
                return true;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static boolean isLeap(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    public static boolean isValidDate(int d, int m, int y) {
        int MIN_VALID_YR = 1800;
        int MAX_VALID_YR = 9999;
        if (y > MAX_VALID_YR || y < MIN_VALID_YR)
            return false;
        if (m < 1 || m > 12)
            return false;
        if (d < 1 || d > 31)
            return false;

        // Handle February month
        // with leap year
        if (m == 2) {
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }

        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);

        return true;
    }

    // Add the data to the csv
    // We first read all the data from the csv
    // Then remove the row of the user who has inputted the data
    // Then add the row of the user with the extra data inputted by the user
    // Then add all the contents into the csv again
    void addData(String location, String checkIn, String checkOut, String room, String ppl) {
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
                recordReaderWhole.add(recordReaderEach);
                if (recordReaderEach[0].equals(user.getUserName())) {
                    locationOfUserInFile = counter;
                }
                counter++;
            }
            recordReaderWhole.remove(locationOfUserInFile);
            recordReaderWhole.add(new String[]{
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
    }
}
