# OOP_Project

## Flow of the Project

* The program starts with the main class
* The main class instantiates an object of the fileControl class and then uses the openLoginScreen function to open the login frame.
* The program then moves to the login frame
* The user then either logs in or sign's up.
  * On Signup, information of the user is taken and stored in a file and then rerouted to login.
  * On login the user is taken to the next screen.
* The user is then taken to a screen asking them for the details of stay
* ...to be continued

---

# Files in the Project Folder **./project**

## User.java
* Contains a User Class
* Is used for creating a user while the application lasts

## FileControl.java
* Contains FileControl Class
* This class is used to switch between frames.
* Contains methods which called can open up a specific frame.

## Login.java
* Initiates login flow.
* Validates the username and password from "users.csv".

## NewUserSignUp.java
* New user addition is implemented here.
* Checks if the username is taken and if the given birthdate is valid (roughly).

## Main.java
* Creates an object of the FileControl Class
* Calls the login screen function to open the login frame
---

## How to compile

* Download and Install [Gradle][1]
* From the root folder run the following commands
    1.`gradle build`
    2.`gradle run`
    
    
[1]: https://gradle.org/install/
