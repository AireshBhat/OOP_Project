package oops.project;

// Create a User Object which the main program will use
// The same user object has to be used the entire time
// the user stays online
public class User {
    static public String name;
    private static String birthday;
    private static String address;
    private static String userName;
    private static String password;
    static public String checkInDate;
    static public String checkOutDate;
    static public String location;
    static public String room;
    static public String ppl;

    // Uncomment these following lines when directly opening a frame from main.java
    public User() {
        // this.name = "Airesh Bhat";
        // this.birthday = "27/12/1997";
        // this.address = "abad,adfad,dva";
        // this.userName = "Airesh9";
        // this.password = "h";
        // this.checkInDate = "22/2/2222";
        // this.checkOutDate = "22/3/2222";
        // this.location = "Udaipur";
        // this.room = "1";
        // this.ppl = "2";
    }

    public User(String userName, String name, String birthday, String password, String address, String checkIn, String checkOut, String room, String ppl, String location) {
        User.name = name;
        User.birthday = birthday;
        User.address = address;
        User.userName = userName;
        User.password = password;
        checkInDate = checkIn;
        checkOutDate = checkOut;
        User.location = location;
        User.room = room;
        User.ppl = ppl;
    }

    // Function to set the name of the user
    public void setName(String n) {
        name = n;
    }

    // Function to get the name of the user
    public String getName() {
        return name;
        // return "Airesh";
    }

    // Function to set the name of the user
    public void setBirthday(String b) {
        birthday = b;
    }

    // Function to get the name of the user
    public String getBirthday() {
        return birthday;
    }

    // Function to set the name of the user
    public void setAddress(String ad) {
        address = ad;
    }

    // Function to get the name of the user
    public String getAddress() {
        return address;
    }

    // Function to set the name of the user
    public void setUserName(String un) {
        userName = un;
    }

    // Function to get the name of the user
    public String getUserName() {
        return userName;
    }

    // Function to set the name of the user
    public void setPassword(String p) {
        password = p;
    }

    // Function to get the name of the user
    public String getPassword() {
        return password;
    }

    // Function to set the check In Date
    public void setCheckInDate(String checkInDate) {
        User.checkInDate = checkInDate;
    }

    // Function to get the check In Date
    public String getCheckInDate() {
        return checkInDate;
    }

    // Function to set the check Out Date
    public void setCheckOutDate(String checkOutDate) {
        User.checkOutDate = checkOutDate;
    }

    // Function to get the check Out Date
    public String getCheckOutDate() {
        return checkOutDate;
    }

    // Function to set the location
    public void setLocation(String location) {
        User.location = location;
    }

    // Function to get the location
    public String getLocation() {
        return location;
    }

    // Function to set the number of rooms
    public void setRoom(String room) {
        User.room = room;
    }

    // Function to get the number of rooms
    public String getRoom() {
        return room;
    }

    // Function to set the number of ppl
    public void setPpl(String ppl) {
        User.ppl = ppl;
    }

    // Function to get the number of ppl
    public String getPpl() {
        return ppl;
    }

}

