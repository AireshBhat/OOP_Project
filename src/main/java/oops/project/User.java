package oops.project;

// Create a User Object which the main program will use
// The same user object has to be used the entire time 
// the user stays online
public class User {
    private String name;
    private String birthday;
    private String address;
    private String userName;
    private String password;
    User(String userName, String name, String birthday, String password, String address) {
      name = name;
      birthday = birthday;
      address = address;
      userName = userName;
      password = password;
    }
    // Function to set the name of the user
    public void setName (String n) {
        name = n;
    };
    // Function to get the name of the user
    public String getName() {
      return name;
    };
    // Function to set the name of the user
    public void setBirthday (String b) {
        birthday = b;
    };
    // Function to get the name of the user
    public String getBirthday() {
      return birthday;
    };
    // Function to set the name of the user
    public void setAddress (String ad) {
        address = ad;
    };
    // Function to get the name of the user
    public String getAddress() {
      return address;
    };
    // Function to set the name of the user
    public void setUserName (String un) {
        userName = un;
    };
    // Function to get the name of the user
    public String getUserName() {
      return userName;
    };
    // Function to set the name of the user
    public void setPassword (String p) {
        password = p;
    };
    // Function to get the name of the user
    public String getPassword() {
      return password;
    };
}

