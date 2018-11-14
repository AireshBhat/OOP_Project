package oops.hotel_list_page;

import oops.project.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Class that implements the design of the frame that asks the user
// the duration of stay, no of hotels and so on
public class HotelStayDetails {
    protected static final String FILE = "./src/main/java/static_files/users.csv";
    // Instantiate an object of the logic class for this class
    private HotelStayDetailsLogic hotelLogic = new HotelStayDetailsLogic();
    private FrameControl fm = new FrameControl();
    private JFrame f = new JFrame();

    private JTextField hOut;
    private JTextField hIn;
    private JTextField noRooms;
    private JTextField noPpl;
    private JTextField location;
    private JLabel hOutLabel;
    private JLabel hInLabel;
    private JLabel noRoomsLabel;
    private JLabel noPplLabel;
    private JLabel userLabel;
    private JLabel locationLabel;
    private JButton submitButton;
    private JButton userPage;
    private JButton logOut;
    private JTextArea invalidDate;

    public HotelStayDetails() {
        User user = new User();

        // Add the Label depicting the name of the user
        String curUserName = User.name;
        userLabel = new JLabel(curUserName);
        userLabel.setBounds(500, 20, 150, 40);
        f.add(userLabel);

        userPage = new JButton("Profile");
        userPage.setBounds(100, 20, 150, 40);
        userPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fm.runUserFrame();
                f.dispose();
            }
        });

        logOut = new JButton("LogOut");
        logOut.setBounds(800, 20, 150, 40);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fm.runLoginScreen();
                f.dispose();
            }
        });
        f.add(userPage);
        f.add(logOut);

        // add the label for the check in date text box
        hInLabel = new JLabel("Check In Date");
        hInLabel.setBounds(300, 150, 150, 40);
        f.add(hInLabel);
        // add the check in date text box
        hIn = new JTextField("DD/MM/YYYY");
        hIn.setBounds(500, 150, 150, 40);
        f.add(hIn);
        if (!("".equals(user.getCheckInDate()))) {
            hIn.setText(User.checkInDate);
        }

        // add the label for the check in date text box
        locationLabel = new JLabel("Location:");
        locationLabel.setBounds(300, 100, 150, 40);
        f.add(locationLabel);
        // add the check in date text box
        location = new JTextField("Location");
        location.setBounds(500, 100, 150, 40);
        f.add(location);
        if (!("".equals(user.getLocation()))) {
            location.setText(User.location);
        }

        // add the label for the check out date text box
        hOutLabel = new JLabel("Check Out Date:");
        hOutLabel.setBounds(300, 200, 150, 40);
        f.add(hOutLabel);
        // add the check out date text box
        hOut = new JTextField("DD/MM/YYYY");
        hOut.setBounds(500, 200, 150, 40);
        f.add(hOut);
        if (!("".equals(user.getCheckOutDate()))) {
            hOut.setText(User.checkOutDate);
        }

        // add the label for the number of rooom
        noRoomsLabel = new JLabel("Number Of Rooms:");
        noRoomsLabel.setBounds(300, 250, 150, 40);
        f.add(noRoomsLabel);
        // add the number of rooms text box
        noRooms = new JTextField("Number of Rooms");
        noRooms.setBounds(500, 250, 150, 40);
        f.add(noRooms);
        if ((!"".equals(user.getRoom()))) {
            noRooms.setText(User.room);
        }

        // add the label for the number of rooom
        noPplLabel = new JLabel("Number of People:");
        noPplLabel.setBounds(300, 300, 150, 40);
        f.add(noPplLabel);
        // add the number of people texr box
        noPpl = new JTextField("Number of People");
        noPpl.setBounds(500, 300, 150, 40);
        f.add(noPpl);
        if (!("".equals(user.getPpl()))) {
            // hIn.setText("10");
            noPpl.setText(User.ppl);
        }

        invalidDate = new JTextArea();
        invalidDate.setBounds(300, 400, 600, 40);
        invalidDate.setVisible(false);
        f.add(invalidDate);

        submitButton = new JButton("Submit");
        submitButton.setBounds(500, 350, 100, 40);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // int checkInDate = hotelLogic.validateDate(checkInDate);
                // int checkOutDate = hotelLogic.validateDate(checkOutDate);
                String[] hInDateInput = hIn.getText().split("[/]");
                String[] hOutDateInput = hOut.getText().split("[/]");
                try {
                    if (!HotelStayDetailsLogic.isValidDate(Integer.parseInt(hInDateInput[0]), Integer.parseInt(hInDateInput[1]), Integer.parseInt(hInDateInput[2]))) {
                        invalidDate.setText("Invalid Check In Date");
                        invalidDate.setVisible(true);
                    } else if (!HotelStayDetailsLogic.isValidDate(Integer.parseInt(hOutDateInput[0]), Integer.parseInt(hOutDateInput[1]), Integer.parseInt(hOutDateInput[2]))) {
                        invalidDate.setText("Invalid CheckOut Date");
                        invalidDate.setVisible(true);
                    } else if (hotelLogic.dateOrder(hIn.getText(), hOut.getText())) {
                        invalidDate.setText("Check In Date is greater than Check Out Date");
                        invalidDate.setVisible(true);
                    } else if (hotelLogic.legitDate(hIn.getText())) {
                        invalidDate.setText("Enter a valid Check In date");
                        invalidDate.setVisible(true);
                    } else if (!noRooms.getText().matches("[0-9]+")) {
                        invalidDate.setText("Enter a number for number of Rooms.");
                        invalidDate.setVisible(true);
                    } else if (!noPpl.getText().matches("[0-9]+")) {
                        invalidDate.setText("Enter a number for number of People");
                        invalidDate.setVisible(true);
                    } else {
                        // invalidDate.setText(hOutDateInput[0]);
                        // invalidDate.setVisible(true);
                        hotelLogic.addData(location.getText(), hIn.getText(), hOut.getText(), noRooms.getText(), noPpl.getText());
                        user.setLocation(location.getText());
                        user.setCheckInDate(hIn.getText());
                        user.setCheckOutDate(hOut.getText());
                        user.setRoom(noRooms.getText());
                        user.setPpl(noPpl.getText());
                        fm.runHotelListFrame();
                        f.dispose();
                    }
                } catch (NumberFormatException ne) {
                    invalidDate.setText("Invalid date type");
                    invalidDate.setVisible(true);
                }
            }
        });

        f.add(submitButton);
        f.setSize(1080, 720);
        f.setLayout(null);
        f.setVisible(true);
    }

}
