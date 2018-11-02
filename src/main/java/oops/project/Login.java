package oops.project;

import com.opencsv.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;

public class Login {

    protected void init() {
        JFrame jFrame = new JFrame();

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
                if (authenticate(userNameTextField.getText(), passwordTextField.getText())) {
                    //login user
                }
                else {
                    invalidLogin.setVisible(true);
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
            while ((record = csvReader.readNext()) != null) {
                if (record[0].equals(username) && record[3].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
