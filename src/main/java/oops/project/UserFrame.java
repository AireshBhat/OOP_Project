package oops.project;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import oops.hotel_list_page.HotelStayDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
      String finalHBookRef = hBookRef;
      cancel[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
          String date = simpleDateFormat.format(Calendar.getInstance().getTime());
          Date date1, date2;
          try {
            date1 = simpleDateFormat.parse(date);
            date2 = simpleDateFormat.parse("22/2/2222");               //TODO: Pass booking date here.
            if (TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) > 3) {
              //TODO: Return method after telling user if more than 3 days
            }
            try {
              CSVReader csvReader = new CSVReader(new FileReader(Booking));
              List<String[]> stringList = new ArrayList<String[]>();
              String[] record;
              while ((record = csvReader.readNext()) != null && !record[7].equals(finalHBookRef)) {
                stringList.add(record);
              }
              csvReader.close();
              CSVWriter csvWriter = new CSVWriter(new FileWriter(Booking, false));
              csvWriter.writeAll(stringList);
              csvWriter.close();
              //TODO: Reload screen.
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          } catch (ParseException e1) {
            e1.printStackTrace();
          }
        }
      });
      String finalCheckIn = checkIn;
      changeDate[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          JDialog jDialog = new JDialog(jFrame, "Enter new dates");
          jDialog.setLayout(null);
          JTextField inDateTextField = new JTextField("DD/MM/YYYY");
          inDateTextField.setBounds(200, 10, 170, 30);
          JLabel dCheckInDate = new JLabel("Check In Date:");
          dCheckInDate.setBounds(50, 10, 170, 30);
          JTextField outDateTextField = new JTextField("DD/MM/YYYY");
          outDateTextField.setBounds(200, 70, 170, 30);
          JLabel dCheckOutDate = new JLabel("Check Out Date:");
          dCheckOutDate.setBounds(50, 70, 170, 30);
          JButton submitButton = new JButton("SUBMIT");
          submitButton.setBounds(120, 120, 125, 50);
          submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              jDialog.setVisible(false);
              if (HotelStayDetails.isValidDate(inDateTextField.toString()) && HotelStayDetails.isValidDate(outDateTextField.toString())) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String date = simpleDateFormat.format(Calendar.getInstance().getTime());
                Date date1, date2;
                try {
                  date1 = simpleDateFormat.parse(date);
                  date2 = simpleDateFormat.parse(finalCheckIn);
                  if (TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS) < 2) {
                    //TODO: 50% charge if less than 2 days
                  }
                  try {
                    CSVReader csvReader = new CSVReader(new FileReader(Booking));
                    List<String[]> stringList = new ArrayList<String[]>();
                    String[] record;
                    while ((record = csvReader.readNext()) != null) {
                      if (record[7].equals(finalHBookRef)) {
                        record[4] = inDateTextField.toString();
                        record[5] = outDateTextField.toString();
                      }
                      stringList.add(record);
                    }
                    csvReader.close();

                    CSVWriter csvWriter = new CSVWriter(new FileWriter(Booking, false));
                    csvWriter.writeAll(stringList);
                    csvWriter.close();
                    //TODO: Reload screen.
                  } catch (IOException e1) {
                    e1.printStackTrace();
                  }
                } catch (ParseException e1) {
                  e1.printStackTrace();
                }
              }
            }
          });
          jDialog.add(inDateTextField);
          jDialog.add(outDateTextField);
          jDialog.add(dCheckInDate);
          jDialog.add(dCheckOutDate);
          jDialog.add(submitButton);
          jDialog.setSize(400, 220);
          jDialog.setVisible(true);
        }
      });
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
