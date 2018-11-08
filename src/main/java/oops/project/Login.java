package oops.project;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class Login {
    // Create an object from user class
    User user;
    protected User init() {
        JFrame jFrame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
        login.setBounds(500, 200, 100, 40);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (authenticate(userNameTextField.getText(), passwordTextField.getText(), user)) {
                    //login user
                    jFrame.dispose();
                }
                else {
                    invalidLogin.setVisible(true);
                    // Open the New User Sign up frame
                    // nus.init();
                    System.out.println();
                    // Close the current log in frame
                    // jFrame.dispose();
                }
            }
        });
        jFrame.add(login);

        JButton newUser = new JButton("SIGN UP");
        newUser.setBounds(500, 250, 100, 40);
        newUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //goto user signup screen
                
                jFrame.dispose();
            }
        });
        jFrame.add(newUser);

        jFrame.setSize(1080, 720);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        return user;
    }

    private boolean authenticate(String username, String password, User user) {
        try {
            FileReader fileReader = new FileReader(NewUserSignup.FILE);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(username) && record[3].equals(password)) {
                    user = new User(record[0], record[1], record[2], record[3], record[4]);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
