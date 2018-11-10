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
    public User() {
    }
    public User(String userName, String name, String birthday, String password, String address) {
      this.name = name;
      this.birthday = birthday;
      this.address = address;
      this.userName = userName;
      this.password = password;
      this.checkInDate = "";
      this.checkOutDate = "";
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
    // Function to set the name of the user
    public void setCheckInDate (String checkInDate) {
        this.checkInDate = checkInDate;
    };
    // Function to get the name of the user
    public String getCheckInDate() {
        return this.checkInDate;
    };
}

