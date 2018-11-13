package oops.project;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class UserFrame {
  User user = new User();
  private static String Booking = "./src/main/java/static_files/booking.csv";
  ArrayList<String[]> listOfHotels = getListOfHotels();
  int numberOfHotels = listOfHotels.size();

  UserFrame() {
    createJPanel();
    createJScrollPane();
    createJFrame();
  }
  FrameControl fm = new FrameControl();
  private JFrame jFrame;
  private JScrollPane jsp;
  private JPanel jp;
  private JLabel[] hotelName = new JLabel[numberOfHotels];
  private JLabel[] hotelAddress = new JLabel[numberOfHotels];
  private JLabel[] hotelPpl = new JLabel[numberOfHotels];
  private JLabel[] hotelAccom = new JLabel[numberOfHotels];
  private JLabel[] hotelPrice = new JLabel[numberOfHotels];
  private JLabel[] hCheckInDate = new JLabel[numberOfHotels];
  private JLabel[] hCheckOutDate = new JLabel[numberOfHotels];
  private JLabel[] hBookingReference = new JLabel[numberOfHotels];
  private JButton[] changeDate = new JButton[numberOfHotels];
  private JButton[] cancel = new JButton[numberOfHotels];

  private void createJPanel() {
    int startingHeight = 170;
    int heightBlock = 300;
    int block = 15;

    jp = new JPanel() {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(1000, startingHeight + (300 * numberOfHotels));
      };
    };

    JLabel name = new JLabel(user.getName());
    name.setBounds(500, 20, 150, 40);
    jp.add(name);
    jp.setLayout(null);

    JLabel currentBookings = new JLabel("Current Bookings");
    currentBookings.setBounds(block * 33, 120, 300, block * 2);
    jp.add(currentBookings);

    JButton newBooking = new JButton("Book Another Hotel");
    newBooking.setBounds(block * 15, 80, 200, block * 2);
    newBooking.addActionListener(new ActionListener() {
      @Override
        public void actionPerformed(ActionEvent e) {
        fm.runHotelStayDetailsFrame();
        jFrame.dispose();
      }
    });
    jp.add(newBooking);

    JButton logOut = new JButton("LogOut");
    logOut.setBounds(block * 40, 80, 200, block * 2);
    logOut.addActionListener(new ActionListener() {
      @Override
        public void actionPerformed(ActionEvent e) {
        fm.runLoginScreen();
        jFrame.dispose();
      }
    });

    jp.add(logOut);

    String hName, hPrice, hAddress, hAccom, hPpl, checkIn, checkOut, hBookRef;

    for (int i = 0; i < numberOfHotels; i++) {
      String[] item = listOfHotels.get(i);
      hName = item[1];
      hAddress = item[2];
      hPpl = item[3];
      checkIn = item[4];
      checkOut = item[5];
      hPrice = item[6];
      hBookRef = item[7];
      hotelName[i] = new JLabel(hName);
      hotelPrice[i] = new JLabel("Total Cost: " + hotelPrice);
      hotelAddress[i] = new JLabel(hAddress);
      // add accom type here
      // :TODO
      hotelAccom[i] = new JLabel("Gold");
      hotelPpl[i] = new JLabel("No. Of Guests: " + hPpl);
      hCheckInDate[i] = new JLabel("In Date: " + checkIn);
      hCheckOutDate[i] = new JLabel("Out Date: " + checkOut);
      hBookingReference[i] = new JLabel("Booking Reference: " + hBookRef);
      changeDate[i] = new JButton("Change Date");
      cancel[i] = new JButton("Cancel Booking");
      hotelName[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block, 300, block * 2);
      hotelAccom[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block, 200, block * 2);
      hotelAddress[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 4, 300, block * 2);
      hotelPpl[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 4, 200, block * 2);
      hCheckInDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 6, 200, block * 2);
      hCheckOutDate[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 6, 200, block * 2);
      hBookingReference[i].setBounds(block * 33, startingHeight + (heightBlock * i) + block * 8, 200, block * 2);
      changeDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
      cancel[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
      // Add the functionality of the two buttons here
      // :TODO
      jp.add(hotelName[i]);
      jp.add(hotelPpl[i]);
      jp.add(hotelAddress[i]);
      jp.add(hotelAccom[i]);
      jp.add(hotelPpl[i]);
      jp.add(hCheckInDate[i]);
      jp.add(hCheckOutDate[i]);
      jp.add(hBookingReference[i]);
      jp.add(changeDate[i]);
      jp.add(cancel[i]);
    }
  }

  private void createJScrollPane() {
    jsp = new JScrollPane(jp) {
      @Override
      public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
      };
    };
    jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jsp.setLocation(0, 0);
    jsp.setSize(1080, 720);
  }

  private void createJFrame() {
    jFrame = new JFrame();
    jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    jFrame.getContentPane().add(jsp);
    jFrame.setSize(1080, 720);
    jFrame.setVisible(true);
  }

  private ArrayList<String[]> getListOfHotels() {
    ArrayList<String []> listOfHotels = new ArrayList<String []>();
    int counter = 0;
    try {
      CSVReader csr = new CSVReader(new FileReader(Booking));
      String[] recordReaderEach;
      while ((recordReaderEach = csr.readNext()) != null) {
        if (recordReaderEach[0].equals(user.getUserName())) {
          counter++;
          listOfHotels.add(recordReaderEach);
        }
      }
      csr.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return listOfHotels;
  }
};
