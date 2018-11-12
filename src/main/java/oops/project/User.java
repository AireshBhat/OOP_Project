package oops.project;

// Create a User Object which the main program will use
// The same user object has to be used the entire time
// the user stays online
public class User {
    static private String name;
    static private String birthday;
    static private String address;
    static private String userName;
    static private String password;
    static private String checkInDate;
    static private String checkOutDate;
    static private String location;
    static private String room;
    static private String ppl;
    public User() {
      this.name = "Airesh Bhat";
      this.birthday = "27/12/1997";
      this.address = "abad,adfad,dva";
      this.userName = "Airesh9";
      this.password = "h";
      this.checkInDate = "22/2/2222";
      this.checkOutDate = "22/3/2222";
      this.location = "Udaipur";
      this.room = "1";
      this.ppl = "2";
    }
    public User(String userName, String name, String birthday, String password, String address, String checkIn, String checkOut, String room, String ppl, String location) {
      this.name = name;
      this.birthday = birthday;
      this.address = address;
      this.userName = userName;
      this.password = password;
      this.checkInDate = checkIn;
      this.checkOutDate = checkOut;
      this.location = location;
      this.room = room;
      this.ppl = ppl;
    }
    // Function to set the name of the user
    public void setName (String n) {
        this.name = n;
    };
    // Function to get the name of the user
    public String getName() {
      return this.name;
      // return "Airesh";
    };
    // Function to set the name of the user
    public void setBirthday (String b) {
        this.birthday = b;
    };
    // Function to get the name of the user
    public String getBirthday() {
      return this.birthday;
    };
    // Function to set the name of the user
    public void setAddress (String ad) {
        this.address = ad;
    };
    // Function to get the name of the user
    public String getAddress() {
      return this.address;
    };
    // Function to set the name of the user
    public void setUserName (String un) {
        this.userName = un;
    };
    // Function to get the name of the user
    public String getUserName() {
      return this.userName;
    };
    // Function to set the name of the user
    public void setPassword (String p) {
        this.password = p;
    };
    // Function to get the name of the user
    public String getPassword() {
      return this.password;
    };
    // Function to set the check In Date
    public void setCheckInDate (String checkInDate) {
        this.checkInDate = checkInDate;
    };
    // Function to get the check In Date
    public String getCheckInDate() {
        return this.checkInDate;
    };
    // Function to set the check Out Date
    public void setCheckOutDate (String checkOutDate) {
        this.checkOutDate = checkOutDate;
    };
    // Function to get the check Out Date
    public String getCheckOutDate() {
        return this.checkOutDate;
    };
    // Function to set the location
    public void setLocation (String location) {
        this.location = location;
    };
    // Function to get the location
    public String getLocation() {
        return this.location;
    };
    // Function to set the number of rooms
    public void setRoom (String room) {
        this.room = room;
    };
    // Function to get the number of rooms
    public String getRoom() {
        return this.room;
    };
    // Function to set the number of ppl
    public void setPpl (String ppl) {
        this.ppl = ppl;
    };
    // Function to get the number of ppl
    public String getPpl() {
        return this.ppl;
    };
}

