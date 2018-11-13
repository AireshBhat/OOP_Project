package oops.hotel_list_page;

import oops.project.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class HotelList {
  User user = new User();
  // Instantiate an object of the logic class for this class
  HotelListLogic hll = new HotelListLogic();
  protected static final String HOTEL_FILE = "./src/main/java/static_files/hotel_info.csv";
  ArrayList<String[]> listOfHotels = hll.hotelList();

  int numberOfHotels = listOfHotels.size();

  JFrame jFrame;
  JPanel jp;
  JScrollPane jsp;
  JDialog jd;
  JLabel[] hotelName = new JLabel[numberOfHotels];
  JLabel[] hotelPrice = new JLabel[numberOfHotels];
  JLabel[] hotelAddress = new JLabel[numberOfHotels];
  JLabel[] hotelAccom = new JLabel[numberOfHotels];
  JLabel[] hotelRating = new JLabel[numberOfHotels];
  JLabel[] hotelType = new JLabel[numberOfHotels];
  JLabel[] hotelReview = new JLabel[numberOfHotels];
  JLabel[] hotelAmenities = new JLabel[numberOfHotels];
  JButton[] hotelBooking = new JButton[numberOfHotels];  
  JButton[] hotelRatingAmenitites = new JButton[numberOfHotels];

  public HotelList () {
    createJPanel();
    createJScrollPane();
    createJFrame();
    // createDialog();
  }

  private void createJPanel() {
    int startingHeight = 50;
    int heightBlock = 300;
    int block = 15;

    jp = new JPanel() {
      @Override
      public Dimension getPreferredSize() {
          return new Dimension(1080, startingHeight + (300 * numberOfHotels));
      };
    };
    // jp.setBounds(0, 0, 1080, 720);
    JLabel name = new JLabel(user.getName());
    name.setBounds(500, 20, 150, 40);
    jp.add(name);
    jp.setLayout(null);
    String hName, hPrice, hAddress, hAccom, hRating, hType;

    for (int i = 0; i < numberOfHotels; i++) {
      String[] item = listOfHotels.get(i);
      hName = item[0];
      hPrice = item[9];
      hAddress = item[1];
      hAccom = item[8];
      hRating = item[3];
      hType = item[7];
      if (!(hName).equals("")) {
        hotelName[i] = new JLabel(hName);
        hotelPrice[i] = new JLabel("Price per night: " + hPrice);
        hotelAddress[i] = new JLabel(hAddress);
        hotelAccom[i] = new JLabel("Accomodates: " + hAccom);
        hotelRating[i] = new JLabel("Ratings: " + hRating);
        hotelType[i] = new JLabel(hType);        
        hotelBooking[i] = new JButton("Book Now!!");
        hotelRatingAmenitites[i] = new JButton("Reviews and Amentities");
        hotelName[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block, 300, block * 2);
        hotelType[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block, 200, block * 2);
        hotelAddress[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 4, 300, block * 2);
        hotelAccom[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 6, 300, block * 2);
        hotelPrice[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block * 5, 200, block * 2);
        hotelRating[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 10, 100, block * 2);
        hotelBooking[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
        hotelRatingAmenitites[i].addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
          }
        });
        jp.add(hotelName[i]);
        jp.add(hotelPrice[i]);
        jp.add(hotelAddress[i]);
        jp.add(hotelAccom[i]);
        jp.add(hotelRating[i]);
        jp.add(hotelType[i]);
        jp.add(hotelBooking[i]);
      }
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
    jsp.setLocation(0,0);
    jsp.setSize(1080, 720);
  }

  private void createJFrame() {
    jFrame = new JFrame("Booking Hotel");
    jFrame.getContentPane().setLayout(null);

    jFrame.getContentPane().add(jsp);
    jFrame.setSize(1080, 720);
    jFrame.setVisible(true);
  }

  private void createDialog(String review, String amenities, int i) {
    String[] reviews = review.split(".");
    jd = new JDialog(jFrame, "Reviews and Amenitites", true);
    jd.setLayout(null);
    hotelReview[i] = new JLabel();
  }

  public void actionPerformed(ActionEvent e){}
}
