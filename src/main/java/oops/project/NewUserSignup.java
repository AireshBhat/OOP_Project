package oops.project;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class NewUserSignup {

    protected static final String FILE = "./users.csv";

    protected void init() {
        JFrame jFrame = new JFrame();

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

        JButton login = new JButton("SUBMIT");
        login.setBounds(500, 300, 100, 40);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = validate(userNameTextField.getText(), birthDateTextField.getText());
                switch (x) {
                    case 1:
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
        jFrame.add(login);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int day = Integer.parseInt(birthDate.substring(0, 1));
            int month = Integer.parseInt(birthDate.substring(3, 4));
            if (day > 31 || month > 12 || day < 1 || month < 1) {
                return -2;
            }
        } catch (NumberFormatException e) {
            return -2;
        }

        return 1;
    }
}
