package oops.hotel_list_page;

import oops.project.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.NumberFormat;
import java.text.ParseException;

public class HotelList {
    private User user = new User();
    // Instantiate an object of the logic class for this class
    private HotelListLogic hll = new HotelListLogic();
    private FrameControl fm = new FrameControl();
    private Booking bk = new Booking();
    // Initializing calendar object
    private Calendar gInDate, gOutDate;
    protected static final String HOTEL_FILE = "./src/main/java/static_files/hotel_info.csv";
   private ArrayList<String[]> listOfHotels = hll.hotelList();

    private int numberOfHotels = listOfHotels.size(), hotelPPN;
    private JFrame jFrame;
    private JFrame jDialog;
    private JPanel jp;
    private JScrollPane jsp;
    private JLabel[] hotelName = new JLabel[numberOfHotels];
    private JLabel[] hotelPrice = new JLabel[numberOfHotels];
    private JLabel[] hotelAddress = new JLabel[numberOfHotels];
    private JLabel[] hotelAccom = new JLabel[numberOfHotels];
    private JLabel[] hotelRating = new JLabel[numberOfHotels];
    private JLabel[] hotelType = new JLabel[numberOfHotels];
    private JButton[] hotelBooking = new JButton[numberOfHotels];
    private JButton[] hotelRatingAmenitites = new JButton[numberOfHotels];
    private JLabel testing = new JLabel("Testing");
    private JLabel reviewDialog = new JLabel();
    private JLabel amenitiesDialog = new JLabel();
    private JLabel reviewDialogList = new JLabel();
    private JLabel amenitiesDialogList = new JLabel();

    public HotelList() {
        createJPanel();
        createJScrollPane();
        createJFrame();
        // createDialog();
    }

    private void createJPanel() {
        int startingHeight = 50;
        int heightBlock = 300;
        int block = 15;

        jp = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1080, startingHeight + (300 * numberOfHotels));
            }

        };
        // jp.setBounds(0, 0, 1080, 720);
        JLabel name = new JLabel(user.getName());
        name.setBounds(500, 20, 150, 40);
        jp.add(name);
        jp.setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 20, 100, 40);
        jp.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fm.runHotelStayDetailsFrame();
                jFrame.dispose();
            }
        });

        backButton.setBounds(100, 20, 100, 40);
        jp.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fm.runHotelStayDetailsFrame();
                jFrame.dispose();
            }
        });
        // testing.setBounds(600, 200, 300, 30);
        // jp.add(testing);
        String hName, hPrice, hAddress, hAccom, hRating, hType;

        for (int i = 0; i < numberOfHotels; i++) {
            String[] item = listOfHotels.get(i);
            hName = item[0];
            hPrice = item[9];
            hAddress = item[1];
            hAccom = item[8];
            hRating = item[3];
            hType = item[7];
            if (!(hName).equals("")) {
                hotelName[i] = new JLabel(hName);
                hotelPrice[i] = new JLabel("Price per night: " + hPrice);
                hotelAddress[i] = new JLabel(hAddress);
                hotelAccom[i] = new JLabel("Accomodates: " + hAccom);
                hotelRating[i] = new JLabel("Ratings: " + hRating);
                hotelType[i] = new JLabel(hType);
                hotelBooking[i] = new JButton("Book Now!!");
                hotelRatingAmenitites[i] = new JButton("Reviews and Amentities");
                hotelName[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block, 300, block * 2);
                hotelType[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block, 200, block * 2);
                hotelAddress[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 4, 300, block * 2);
                hotelAccom[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 6, 300, block * 2);
                hotelPrice[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block * 5, 200, block * 2);
                hotelRating[i].setBounds(block * 4, startingHeight + (heightBlock * i) + block * 10, 100, block * 2);
                hotelBooking[i].setBounds(block * 26, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
                hotelRatingAmenitites[i].setBounds(block * 10, startingHeight + (heightBlock * i) + block * 10, 200, block * 2);
                hotelRatingAmenitites[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createDialog(item[5], item[4]);
                    }
                });
                hotelBooking[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String[] parsedInDate = user.getCheckInDate().split("/");
                        int gInDay = Integer.parseInt(parsedInDate[0]);
                        int gInMonth = Integer.parseInt(parsedInDate[1]);
                        int gInYear = Integer.parseInt(parsedInDate[2]);
                        String[] parsedOutDate = user.getCheckOutDate().split("/");
                        int gOutDay = Integer.parseInt(parsedOutDate[0]);
                        int gOutMonth = Integer.parseInt(parsedOutDate[1]);
                        int gOutYear = Integer.parseInt(parsedOutDate[2]);
                        // gInDate = new GregorianCalendar(gInYear, gInMonth, gInDay);
                        // gOutDate = new GregorianCalendar(gOutYear, gOutMonth, gOutDay);
                        gInDate = Calendar.getInstance();
                        gOutDate = Calendar.getInstance();
                        gInDate.set(gInYear, gInMonth, gInDay);
                        gOutDate.set(gOutYear, gOutMonth, gOutDay);
                        Date startDate = gInDate.getTime();
                        Date endDate = gOutDate.getTime();
                        long startTime = startDate.getTime();
                        long endTime = endDate.getTime();
                        long diffTime = endTime - startTime;
                        long diffDays = diffTime / (1000 * 60 * 60 * 24);
                        String testingString = item[9];
                        try {
                            // hotelPPN = Integer.parseInt(testingString);
                            hotelPPN = NumberFormat.getNumberInstance(java.util.Locale.US).parse(item[9]).intValue();
                        } catch (ParseException ee) {
                            // testing.setText("number format");
                            ee.printStackTrace();
                        }
                        long totalCost = diffDays * hotelPPN * (long) Integer.valueOf(user.getRoom());
                        testing.setText(Long.toString(totalCost));
                        bk.init(item[0], Long.toString(totalCost), item[1], item[8], item[7], hotelPPN);
                        jFrame.dispose();
                    }
                });
                jp.add(hotelName[i]);
                jp.add(hotelPrice[i]);
                jp.add(hotelAddress[i]);
                jp.add(hotelAccom[i]);
                jp.add(hotelRating[i]);
                jp.add(hotelType[i]);
                jp.add(hotelBooking[i]);
                jp.add(hotelRatingAmenitites[i]);
            }
        }
    }

    private void createJScrollPane() {
        jsp = new JScrollPane(jp) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1080, 720);
            }

        };
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setLocation(0, 0);
        jsp.setSize(1080, 720);
    }

    private void createJFrame() {
        jFrame = new JFrame("Booking Hotel");
        jFrame.getContentPane().setLayout(null);

        jFrame.getContentPane().add(jsp);
        jFrame.setSize(1080, 720);
        jFrame.setVisible(true);
    }

    private void createDialog(String review, String amenities) {
        if (jDialog == null) {
            jDialog = new JFrame();
        }
        jDialog.setSize(450, 400);
        jDialog.setLocation(0, 0);
        jDialog.setAlwaysOnTop(true);

        String newline = System.getProperty("line.separator");
        String[] reviewList = review.split("\\s+");
        StringBuilder reviewsBuilder = new StringBuilder("<html>");
        for (int j = 0; j < reviewList.length; j += 9) {
            // reviews += reviewList[0 + j] + reviewList[1 + j]+ reviewList[2 + j] + "<br>";
            reviewsBuilder.append((j) < reviewList.length ? reviewList[j] + " " : "");
            reviewsBuilder.append((1 + j) < reviewList.length ? reviewList[1 + j] + " " : "");
            reviewsBuilder.append((2 + j) < reviewList.length ? reviewList[2 + j] + " " : "");
            reviewsBuilder.append((3 + j) < reviewList.length ? reviewList[3 + j] + " " : "");
            reviewsBuilder.append((4 + j) < reviewList.length ? reviewList[4 + j] + " " : "");
            reviewsBuilder.append((5 + j) < reviewList.length ? reviewList[5 + j] + " " : "");
            reviewsBuilder.append((6 + j) < reviewList.length ? reviewList[6 + j] + " " : "");
            reviewsBuilder.append((7 + j) < reviewList.length ? reviewList[7 + j] + " " : "");
            reviewsBuilder.append((8 + j) < reviewList.length ? reviewList[8 + j] + " " : "");
            reviewsBuilder.append("<br>");
            // reviews += (Integer.toString(j + 1)) + ". " + reviewList[j] + "\n";
        }
        String reviews = reviewsBuilder.toString();
        reviews += "</html>";
        String[] amenitiesList = amenities.split("[*]");
        StringBuilder amenitiesBuilder = new StringBuilder("<html>");
        for (int j = 0; j < amenitiesList.length; j++) {
            amenitiesBuilder.append(Integer.toString(j + 1)).append(". ").append(amenitiesList[j]).append("<br>");
        }
        amenities = amenitiesBuilder.toString();
        amenities += "</html>";
        // testing.setText(amenities);

        reviewDialog.setText("Review 1");
        reviewDialog.setBounds(250, 10, 300, 70);
        jDialog.add(reviewDialog);

        amenitiesDialog.setText("Amenities");
        amenitiesDialog.setBounds(10, 10, 140, 70);
        jDialog.add(amenitiesDialog);

        reviewDialogList.setText(reviews);
        reviewDialogList.setBounds(250, 80, 150, 200);
        jDialog.add(reviewDialogList);

        amenitiesDialogList.setText(amenities);
        amenitiesDialogList.setBounds(0, 0, 0, 0);
        jDialog.add(amenitiesDialogList);
        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        jDialog.setVisible(true);


        // hotelReview[i] = new JLabel();
    }

}
