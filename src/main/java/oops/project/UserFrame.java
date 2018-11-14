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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserFrame {
  User user = new User();
  private static String Booking = "./src/main/java/static_files/booking.csv";
  static int MAX_VALID_YR = 9999; 
  static int MIN_VALID_YR = 1800; 
  ArrayList<String[]> listOfHotels = getListOfHotels();

  Calendar gInDate, gOutDate;
  int numberOfHotels = listOfHotels.size();
  int hotelPPN = 0;
  int totalCost = 0;

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

    // JLabel testing = new JLabel("Testing");
    // testing.setBounds(600, 200, 300, 40);
    // jp.add(testing);

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
    int pricePerNight = 0;

    for (int i = 0; i < numberOfHotels; i++) {
      String[] item = listOfHotels.get(i);
      hName = item[1];
      hAddress = item[2];
      hPpl = item[3];
      checkIn = item[4];
      checkOut = item[5];
      // pricePerNight = item[6];

      // Calculate the Total Cost even on change of dates
      String[] parsedInDate = item[4].split("/");
      int gInDay = Integer.parseInt(parsedInDate[0]);
      int gInMonth = Integer.parseInt(parsedInDate[1]);
      int gInYear = Integer.parseInt(parsedInDate[2]);
      String[] parsedOutDate = item[5].split("/");
      int gOutDay = Integer.parseInt(parsedOutDate[0]);
      int gOutMonth = Integer.parseInt(parsedOutDate[1]);
      int gOutYear = Integer.parseInt(parsedOutDate[2]);
      gInDate = Calendar.getInstance();
      gOutDate = Calendar.getInstance();
      gInDate.set(gInYear, gInMonth, gInDay);
      gOutDate.set(gOutYear, gOutMonth, gOutDay);
      Date startDate = gInDate.getTime();
      Date endDate = gOutDate.getTime();
      long startTime = startDate.getTime();
      long endTime = endDate.getTime();
      long diffTime = endTime - startTime;
      long diffDays = diffTime / (1000 * 60 * 60 * 24);
      try {
        hotelPPN = NumberFormat.getNumberInstance(java.util.Locale.US).parse(item[6]).intValue();
      } catch (ParseException ee){
        // testing.setText("numberFormat");
        ee.printStackTrace();
      }
      long totalCost = diffDays * hotelPPN * (long)Integer.valueOf(user.getRoom());

      hPrice = item[6];
      hBookRef = item[7];
      hotelName[i] = new JLabel(hName);
      hotelPrice[i] = new JLabel("Total Cost: " + totalCost);
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
      hCheckInDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 8, 200, block * 2);
      hCheckOutDate[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 8, 200, block * 2);
      hBookingReference[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
      hotelPrice[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
      changeDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 12, 200, block * 2);
      cancel[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 12, 200, block * 2);
      String finalHBookRef = hBookRef;
      cancel[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
          String date = simpleDateFormat.format(Calendar.getInstance().getTime());
          Date date1, date2;
          try {
            date1 = simpleDateFormat.parse(date);
            date2 = simpleDateFormat.parse(user.getCheckInDate());
            if (TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS) < 2) {
              JDialog jDialog = new JDialog(jFrame, "Alert");
              jDialog.setLayout(null);
              JTextField alertTextField = new JTextField("You will be charged 50% of the total fee!");
              jDialog.add(alertTextField);
              jDialog.setVisible(true);
            }
            try {
                hotelPPN = NumberFormat.getNumberInstance(java.util.Locale.US).parse(item[6]).intValue();
            } catch (ParseException ee) {
                // testing.setText("numberFormat");
                ee.printStackTrace();
            }
            long totalCost = diffDays * hotelPPN * (long) Integer.valueOf(user.getRoom());

            hBookRef = item[7];
            hotelName[i] = new JLabel(hName);
            hotelPrice[i] = new JLabel("Total Cost: " + totalCost);
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
            hCheckInDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 8, 200, block * 2);
            hCheckOutDate[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 8, 200, block * 2);
            hBookingReference[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
            hotelPrice[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
            changeDate[i].setBounds(block * 20, startingHeight + (heightBlock * i) + block * 12, 200, block * 2);
            cancel[i].setBounds(block * 42, startingHeight + (heightBlock * i) + block * 12, 200, block * 2);
            String finalHBookRef = hBookRef;
            cancel[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = simpleDateFormat.format(Calendar.getInstance().getTime());
                    Date date1, date2;
                    try {
                        date1 = simpleDateFormat.parse(date);
                        date2 = simpleDateFormat.parse(user.getCheckInDate());
                        if (TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS) < 2) {
                            JDialog jDialog = new JDialog(jFrame, "Alert");
                            jDialog.setLayout(null);
                            JTextField alertTextField = new JTextField("You will be charged 50% of the total fee!");
                            jDialog.add(alertTextField);
                            jDialog.setVisible(true);
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
                            fm.runUserFrame();
                            jFrame.dispose();
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
                    JLabel errorLabel = new JLabel("Error");
                    errorLabel.setBounds(120, 120, 125, 50);
                    errorLabel.setVisible(false);
                    JButton submitButton = new JButton("SUBMIT");
                    submitButton.setBounds(120, 160, 125, 50);
                    submitButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jDialog.setVisible(false);
                            if (!isValidDate(inDateTextField.getText())) {
                                errorLabel.setText("Enter Valid Check In Date.");
                                errorLabel.setVisible(true);
                            } else if (!isValidDate(outDateTextField.getText())) {
                                errorLabel.setText("Enter Valid Check Out Date.");
                                errorLabel.setVisible(true);
                            } else if (dateOrder(inDateTextField.getText(), outDateTextField.getText())) {
                                errorLabel.setText("Check In Date must be before Check Out");
                                errorLabel.setVisible(true);
                            } else if ((legitDate(inDateTextField.getText()) || legitDate(outDateTextField.getText()))) {
                                errorLabel.setText("Enter Valid Date");
                                errorLabel.setVisible(true);
                            } else {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                String date = simpleDateFormat.format(Calendar.getInstance().getTime());
                                Date date1, date2 = null;
                                try {
                                    CSVReader csvReader = new CSVReader(new FileReader(Booking));
                                    String[] record;
                                    while ((record = csvReader.readNext()) != null) {
                                        if (record[7].equals(finalHBookRef)) {
                                            date2 = simpleDateFormat.parse(record[8]);
                                            break;
                                        }
                                    }
                                    if (date2 == null) {
                                        return; //IO error
                                    }
                                    date1 = simpleDateFormat.parse(date);
                                    if (TimeUnit.DAYS.convert(date1.getTime() - date2.getTime(), TimeUnit.MILLISECONDS) > 3) {
                                        JOptionPane.showMessageDialog(jp, "Cannot change booking after 3 days from booking.", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                    csvReader = new CSVReader(new FileReader(Booking));
                                    List<String[]> stringList = new ArrayList<String[]>();
                                    while ((record = csvReader.readNext()) != null) {
                                        if (record[7].equals(finalHBookRef)) {
                                            record[4] = inDateTextField.getText();
                                            record[5] = outDateTextField.getText();
                                        }
                                        stringList.add(record);
                                    }
                                    csvReader.close();

                                    CSVWriter csvWriter = new CSVWriter(new FileWriter(Booking, false));
                                    csvWriter.writeAll(stringList);
                                    csvWriter.close();
                                    fm.runUserFrame();
                                    jFrame.dispose();
                                } catch (IOException | ParseException e1) {
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
                    jDialog.add(errorLabel);
                    jDialog.setSize(400, 260);
                    jDialog.setVisible(true);
                }
            });
            jp.add(hotelName[i]);
            jp.add(hotelPpl[i]);
            jp.add(hotelPrice[i]);
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
            }

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
        ArrayList<String[]> listOfHotels = new ArrayList<String[]>();
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

    private static boolean isValidDate(String enteredDate) {
        String[] enteredDateSplit = enteredDate.split("[/]");
        int d, m, y;
        try {
            d = Integer.parseInt(enteredDateSplit[0]);
            m = Integer.parseInt(enteredDateSplit[1]);
            y = Integer.parseInt(enteredDateSplit[2]);
            int MIN_VALID_YR = 1800;
            int MAX_VALID_YR = 9999;
            if (y > MAX_VALID_YR || y < MIN_VALID_YR)
                return false;
            if (m < 1 || m > 12)
                return false;
            if (d < 1 || d > 31)
                return false;
        } catch (NumberFormatException ne) {
            return false;
        }
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

    // check whether the data input from the user is valid
    private boolean dateOrder(String checkIn, String checkOut) {
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

    private boolean legitDate(String checkIn) {
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
}
