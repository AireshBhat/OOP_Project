package oops.hotel_list_page;

import oops.project.*;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

// Class that implements the design of the frame that asks the user
// the duration of stay, no of hotels and so on
public class HotelStayDetails {
  protected static final String FILE = "./src/main/java/static_files/users.csv";
  // Instantiate an object of the logic class for this class
  HotelStayDetailsLogic hotelLogic = new HotelStayDetailsLogic();
  FrameControl fm = new FrameControl();
  JFrame f = new JFrame();

  JTextField hOut, hIn, noRooms, noPpl, location;
  JLabel hOutLabel, hInLabel, noRoomsLabel, noPplLabel, userLabel, locationLabel;
  JButton submitButton;
  JTextArea invalidDate;
  public HotelStayDetails() {
    User user = new User();

    // Add the Label depicting the name of the user
    String curUserName = user.getName();
    userLabel = new JLabel("Name: " + curUserName);
    userLabel.setBounds(500, 20, 150, 40);
    f.add(userLabel);
    // add the label for the check in date text box
    hInLabel = new JLabel("Check In Date");
    hInLabel.setBounds(300, 100, 150, 40);
    f.add(hInLabel);
    // add the check in date text box
    hIn = new JTextField("DD/MM/YYYY");
    hIn.setBounds(500, 100, 150, 40);
    f.add(hIn);

    // add the label for the check in date text box
    locationLabel = new JLabel("Location");
    locationLabel.setBounds(300, 50, 150, 40);
    f.add(locationLabel);
    // add the check in date text box
    location = new JTextField("DD/MM/YYYY");
    location.setBounds(500, 50, 150, 40);
    f.add(location);

    // add the label for the check out date text box
    hOutLabel = new JLabel("Check Out Date");
    hOutLabel.setBounds(300, 150, 150, 40);
    f.add(hOutLabel);
    // add the check out date text box
    hOut = new JTextField("DD/MM/YYYY");
    hOut.setBounds(500, 150, 150, 40);
    f.add(hOut);

    // add the label for the number of rooom
    noRoomsLabel = new JLabel("Number Of Rooms");
    noRoomsLabel.setBounds(300, 200, 150, 40);
    f.add(noRoomsLabel);
    // add the number of rooms text box
    noRooms = new JTextField("Number of Rooms");
    noRooms.setBounds(500, 200, 150, 40);
    f.add(noRooms);

    // add the label for the number of rooom
    noPplLabel = new JLabel("Number of People");
    noPplLabel.setBounds(300, 250, 150, 40);
    f.add(noPplLabel);
    // add the number of people texr box
    noPpl = new JTextField("Number of People");
    noPpl.setBounds(500, 250, 150, 40);
    f.add(noPpl);

    invalidDate = new JTextArea();
    invalidDate.setBounds(500,350, 150, 40);
    invalidDate.setVisible(false);
    f.add(invalidDate);

    submitButton = new JButton("Submit");
    submitButton.setBounds(500, 300, 100, 40);
    submitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // int checkInDate = hotelLogic.validateDate(checkInDate);
        // int checkOutDate = hotelLogic.validateDate(checkOutDate);
        // TODO:
        // Custom error display for each wrong case
        if (hotelLogic.validate(hIn.getText(), hOut.getText(), noRooms.getText(), noPpl.getText()) != 1) {
          invalidDate.setText("Invalid Date Format");
          invalidDate.setVisible(true);
        } else {
          hotelLogic.addData(location.getText(), hIn.getText(), hOut.getText(), noRooms.getText(), noPpl.getText());
          f.dispose();
          fm.runHotelListFrame();
          // fm.closeHotelStayDetailsFrame();
        }
      }
    });

    f.add(submitButton);
    f.setSize(1080, 720);
    f.setLayout(null);
    f.setVisible(true);
  }
  public void actionPerformed(ActionEvent e) {}
}
