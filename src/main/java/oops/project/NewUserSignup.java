package oops.project;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import oops.hotel_list_page.HotelStayDetails;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserSignup {

    protected static final String FILE = "./src/main/java/static_files/users.csv";

    protected void init() {
        FrameControl fc = new FrameControl();
        JFrame jFrame = new JFrame("New User SignUp");

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 20, 100, 40);
        jFrame.add(backButton);
        backButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            fc.runLoginScreen();
            jFrame.dispose();
          }
        });

        JTextField nameTextField = new JTextField("Name");
        nameTextField.setBounds(500, 100, 100, 40);
        jFrame.add(nameTextField);

        JTextField birthDateTextField = new JTextField("Birthdate: DD/MM/YYYY");
        birthDateTextField.setBounds(500, 150, 100, 40);
        jFrame.add(birthDateTextField);

        JTextField addressTextField = new JTextField("Address");
        addressTextField.setBounds(500, 200, 300, 40);
        jFrame.add(addressTextField);

        JTextField userNameTextField = new JTextField("Username");
        userNameTextField.setBounds(500, 250, 100, 40);
        jFrame.add(userNameTextField);

        JTextField passwordTextField = new JTextField("Password");
        passwordTextField.setBounds(500, 300, 100, 40);
        jFrame.add(passwordTextField);

        JTextArea invalidLogin = new JTextArea();
        invalidLogin.setBounds(500, 350, 350, 40);
        invalidLogin.setVisible(false);
        jFrame.add(invalidLogin);

        JButton loginButton = new JButton("SUBMIT");
        loginButton.setBounds(500, 400, 100, 40);
        jFrame.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = validate(userNameTextField.getText(), birthDateTextField.getText());
                switch (x) {
                    case 1:
                        try {
                            FileWriter fileWriter = new FileWriter(FILE, true);
                            CSVWriter csvWriter = new CSVWriter(fileWriter);
                            String[] data = {
                              userNameTextField.getText(),
                              nameTextField.getText(),
                              birthDateTextField.getText(),
                              passwordTextField.getText(),
                              addressTextField.getText(),
                              "",
                              "",
                              "",
                              "",
                              "",
                            };
                            csvWriter.writeNext(data);
                            csvWriter.close();
                            fileWriter.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        fc.runLoginScreen();
                        jFrame.dispose();
                        //login user
                        break;
                    case -1:
                        invalidLogin.setText("Username is already taken.");
                        invalidLogin.setVisible(true);
                        break;
                    case -2:
                        invalidLogin.setText("Invalid birthdate");
                        invalidLogin.setVisible(true);
                        break;
                }
            }
        });

        jFrame.setSize(1080, 720);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    private int validate(String userId, String birthDate) {
        try {
            FileReader fileReader = new FileReader(FILE);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(userId)) {
                    return -1;
                }
            }
          csvReader.close();
          fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!HotelStayDetails.isValidDate(birthDate)) {
            return -2;
        }

        return 1;
    }
}
