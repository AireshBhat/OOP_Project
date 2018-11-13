package oops.project;

import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Booking {

    private final String BOOKING = "./src/main/java/static_files/booking.csv";

    protected void init(String hName, String hPrice, String hAddress, String hAccom) {
        User user = new User();
        String username = user.getUserName();
        JFrame jFrame = new JFrame();
        JTextArea hotelDisplayText = new JTextArea(hName + "\n" + hAddress + "\n" + hAccom + "\n" + hPrice);
        hotelDisplayText.setBounds(500, 100, 350, 160);
        jFrame.add(hotelDisplayText);

        JTextField idProofTextField = new JTextField("Aadhar Number");
        idProofTextField.setBounds(500, 300, 100, 40);
        jFrame.add(idProofTextField);

        JButton bookButton = new JButton("BOOK");
        bookButton.setBounds(500, 350, 100, 40);
        jFrame.add(bookButton);
        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int rand = random.nextInt(1000000);
                try {
                    FileWriter fileWriter = new FileWriter(BOOKING, true);
                    CSVWriter csvWriter = new CSVWriter(fileWriter);
                    String[] data = {username, hName, hAddress, hAccom, inDate, outDate, hPrice, String.valueOf(rand)};
                    csvWriter.writeNext(data);
                    csvWriter.close();
                    fileWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
