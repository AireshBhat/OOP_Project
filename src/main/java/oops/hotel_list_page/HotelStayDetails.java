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
public class HotelStayDetails extends JFrame implements ActionListener {
  String name;

  JTextField hOut, hIn, noRooms, noPpl;
  JLabel hOutLabel, hInLabel, noRoomsLabel, noPplLabel, userLabel;
  JButton submitButton;
  public HotelStayDetails(String username) {
    User user = new User();
    
    // Add the Label depicting the name of the user
    String curUserName = user.getName();
    userLabel = new JLabel("Name: " + curUserName);
    userLabel.setBounds(500, 20, 150, 40);
    add(userLabel);
    // add the label for the check in date text box
    hInLabel = new JLabel("Check In Date");
    hInLabel.setBounds(300, 100, 150, 40);
    add(hInLabel);
    // add the check in date text box
    hIn = new JTextField("Check In Date");
    hIn.setBounds(500, 100, 150, 40);
    add(hIn);

    // add the label for the check out date text box
    hOutLabel = new JLabel("Check Out Date");
    hOutLabel.setBounds(300, 150, 150, 40);
    add(hOutLabel);
    // add the check out date text box
    hOut = new JTextField("Check Out Date");
    hOut.setBounds(500, 150, 150, 40);
    add(hOut);

    // add the label for the number of rooom
    noRoomsLabel = new JLabel("Number Of Rooms");
    noRoomsLabel.setBounds(300, 200, 150, 40);
    add(noRoomsLabel);
    // add the number of rooms text box
    noRooms = new JTextField("Number of Rooms");
    noRooms.setBounds(500, 200, 150, 40);
    add(noRooms);

    // add the label for the number of rooom
    noPplLabel = new JLabel("Number of People");
    noPplLabel.setBounds(300, 250, 150, 40);
    add(noPplLabel);
    // add the number of people texr box
    noPpl = new JTextField("Number of People");
    noPpl.setBounds(500, 250, 150, 40);
    add(noPpl);

    submitButton = new JButton("Submit");
    submitButton.setBounds(500, 300, 100, 40);
    submitButton.addActionListener(this);
    add(submitButton);
    setSize(1080, 720);
    setLayout(null);
    setVisible(true);
  }

    public void actionPerformed(ActionEvent e) {
      submitButton.setText("sent");
    } 
}
