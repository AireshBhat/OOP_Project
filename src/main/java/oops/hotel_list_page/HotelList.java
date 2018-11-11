package oops.hotel_list_page;

import oops.project.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class HotelList extends JFrame implements ActionListener {
  User user = new User();
  // Instantiate an object of the logic class for this class
  HotelListLogic hll = new HotelListLogic();
  protected static final String HOTEL_FILE = "./src/main/java/static_files/hotel_info.csv";
  ArrayList<String[]> listOfHotels = hll.hotelList();

  JTextArea name;
  public HotelList () {
    name = new JTextArea(user.getName());
    name.setBounds(500, 20, 150, 40);
    add(name);

    setSize(1080, 720);
    setLayout(null);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e){}
}
