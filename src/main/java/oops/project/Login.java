package oops.project;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

class Login {
    // Create an object from user class
    private FrameControl fm = new FrameControl();

    void init() {
        JFrame jFrame = new JFrame("Login");
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JTextField userNameTextField = new JTextField("Username");
        userNameTextField.setBounds(500, 100, 100, 40);
        jFrame.add(userNameTextField);

        JTextField passwordTextField = new JTextField("Password");
        passwordTextField.setBounds(500, 150, 100, 40);
        jFrame.add(passwordTextField);

        JTextArea invalidLogin = new JTextArea("Username and password do not match.");
        invalidLogin.setBounds(500, 300, 300, 40);
        invalidLogin.setVisible(false);
        jFrame.add(invalidLogin);

        JButton login = new JButton("SUBMIT");
        login.setBounds(400, 200, 100, 40);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticate(userNameTextField.getText(), passwordTextField.getText())) {
                    //login user
                    fm.runHotelStayDetailsFrame();
                    jFrame.dispose();
                } else {
                    invalidLogin.setVisible(true);
                    // jFrame.dispose();
                    // Open the New User Sign up frame
                    // nus.init();
                    // Close the current log in frame
                    // jFrame.dispose();
                }
            }
        });
        jFrame.add(login);

        JButton newUser = new JButton("SIGN UP");
        newUser.setBounds(600, 200, 100, 40);
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //goto user signup screen

                // Open up the signup user frame.
                fm.runNewUserSignup();
                // Close the current frame.
                jFrame.dispose();
            }
        });
        jFrame.add(newUser);

        jFrame.setSize(1080, 720);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

    private boolean authenticate(String username, String password) {
        try {
            FileReader fileReader = new FileReader(NewUserSignup.FILE);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;
            int recordLength = 0;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(username) && record[3].equals(password)) {
                    User user = new User(
                            record[0],
                            record[1],
                            record[2],
                            record[3],
                            record[4],
                            record[5],
                            record[6],
                            record[7],
                            record[8],
                            record[9]
                    );
                    return true;
                }
            }
            csvReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
