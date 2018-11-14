package oops.project;

import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Booking {

    private final String BOOKING = "./src/main/java/static_files/booking.csv";

    public void init(String hName, String hPrice, String hAddress, String hAccom, String hType, int pricePerNight) {
        User user = new User();
        FrameControl fm = new FrameControl();
        String username = user.getUserName();
        JFrame jFrame = new JFrame("Book Hotel");
        // JTextArea hotelDisplayText = new JTextArea(hName + "\n" + hAddress + "\n" + hAccom + "\n" + hPrice);
        // hotelDisplayText.setBounds(450, 100, 350, 160);
        // jFrame.add(hotelDisplayText);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 20, 100, 40);
        jFrame.add(backButton);
        backButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            fm.runHotelListFrame();
            jFrame.dispose();
          }
        });

        JLabel hotelNameLabel = new JLabel("Hotel Name:");
        hotelNameLabel.setBounds(250, 100, 200, 40);
        jFrame.add(hotelNameLabel);

        JLabel hotelName = new JLabel(hName);
        hotelName.setBounds(450, 100, 300, 40);
        jFrame.add(hotelName);

        JLabel hotelAddressLabel = new JLabel("Address:");
        hotelAddressLabel.setBounds(250, 150, 200, 40);
        jFrame.add(hotelAddressLabel);

        JLabel hotelAddress = new JLabel(hAddress);
        hotelAddress.setBounds(450, 150, 400, 40);
        jFrame.add(hotelAddress);

        JLabel hotelAccomLabel = new JLabel("Accom Type:");
        hotelAccomLabel.setBounds(250, 200, 200, 40);
        jFrame.add(hotelAccomLabel);

        JLabel hotelAccom = new JLabel(hAccom);
        hotelAccom.setBounds(550, 200, 200, 40);
        jFrame.add(hotelAccom);

        JLabel hotelCheckInLabel = new JLabel("Check In Date:");
        hotelCheckInLabel.setBounds(250, 250, 200, 40);
        jFrame.add(hotelCheckInLabel);

        JLabel hotelCheckIn = new JLabel(user.getCheckInDate());
        hotelCheckIn.setBounds(450, 250, 200, 40);
        jFrame.add(hotelCheckIn);

        JLabel hotelCheckOutLabel = new JLabel("Check Out Date:");
        hotelCheckOutLabel.setBounds(250, 300, 200, 40);
        jFrame.add(hotelCheckOutLabel);

        JLabel hotelCheckOut = new JLabel(user.getCheckOutDate());
        hotelCheckOut.setBounds(450, 300, 200, 40);
        jFrame.add(hotelCheckOut);

        JLabel hotelPriceLabel = new JLabel("Total Cost:");
        hotelPriceLabel.setBounds(250, 350, 200, 40);
        jFrame.add(hotelPriceLabel);

        JLabel hotelPrice = new JLabel(hPrice);
        hotelPrice.setBounds(450, 350, 200, 40);
        jFrame.add(hotelPrice);

        JTextField idProofTextField = new JTextField("Aadhar Number");
        idProofTextField.setBounds(450, 400, 200, 40);
        jFrame.add(idProofTextField);

        JButton bookButton = new JButton("BOOK");
        bookButton.setBounds(500, 450, 100, 40);
        jFrame.add(bookButton);
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int rand = random.nextInt(1000000);
                try {
                    FileWriter fileWriter = new FileWriter(BOOKING, true);
                    CSVWriter csvWriter = new CSVWriter(fileWriter);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String date = simpleDateFormat.format(Calendar.getInstance().getTime());
                    String[] data = {username, hName, hAddress, hAccom, user.getCheckInDate(), user.getCheckOutDate(), hPrice, String.valueOf(rand), date, hType, Integer.toString(pricePerNight)};
                    csvWriter.writeNext(data);
                    csvWriter.close();
                    fileWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                fm.runUserFrame();
                jFrame.dispose();
            }
        });
        jFrame.setSize(1080, 720);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }
}
