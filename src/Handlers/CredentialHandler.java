package Handlers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Entities.DatabaseEntities.User;
import Factories.Factory;
import Main.Main;
import Util.Connect;
import View.CustomerDashboardPage;
import View.SelectRolePage;
import View.StaffHomePage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Toggle;
import javafx.stage.Stage;

public class CredentialHandler {

	private static Connect db = Connect.getInstance();
	private static Boolean loggedInAsCustomer = false;
	private static Boolean loggedInAsStaff = false;

	public CredentialHandler() {
		// TODO Auto-generated constructor stub
	}

//	public static ArrayList<User> fetchCustomer() {
//		ArrayList<Customer> customerList = new ArrayList<>();
//		String query = "SELECT * FROM MsUser";
//		db.rs = db.execQuery(query);
//		try {
//			while (db.rs.next()) {
//
//				int customerID = db.rs.getInt("UserID");
//				String customerDOB = db.rs.getString("CustomerDOB");
//				String customerEmail = db.rs.getString("CustomerEmail");
//				String customerPassword = db.rs.getString("CustomerPassword");
//				String customerGender = db.rs.getString("CustomerGender");
//
//				customerList
//						.add(new User(customerID, customerDOB, customerEmail, customerPassword, customerGender));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return customerList;
//	}

	public static ArrayList<User> fetchUser() {
		ArrayList<User> userList = new ArrayList<>();
		String query = "SELECT * FROM MsUser";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int userID = db.rs.getInt("UserID");
				String userDOB = db.rs.getString("UserDOB");
				String userEmail = db.rs.getString("UserEmail");
				String userPassword = db.rs.getString("UserPassword");
				String userGender = db.rs.getString("UserGender");
				String userRole = db.rs.getString("UserRole");

				userList.add(new User(userID, userDOB, userEmail, userPassword, userGender, userRole));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userList;
	}

	public static void checkUser(String email, String password) {
		ArrayList<User> userList = fetchUser();
		for (User user : userList) {
			if (user.getUserEmail().equals(email) && user.getUserPassword().equals(password)) {
				if (user.getUserRole().equalsIgnoreCase("manager")) {
					loggedInAsStaff = true;
				} else {
					loggedInAsCustomer = true;
				}

				Main.currentUser = user;
				break;
			}
		}
	}

	public static boolean uniqueUser(String email) {

		ArrayList<User> userList = fetchUser();

		for (User user : userList) {
			if (user.getUserEmail().equals(email))
				return false;
		}

		return true;
	}

	public static void handleLogin(Stage primaryStage, String email, String password) {

		if (email.equals("") || password.equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Login");
			alert.setHeaderText("Log in failed");
			alert.setContentText("Please fill out all fields.");
			alert.showAndWait();
			return;
		}

		loggedInAsCustomer = false;
		loggedInAsStaff = false;

		checkUser(email, password);

		if (!loggedInAsCustomer && !loggedInAsStaff) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Login");
			alert.setHeaderText("Wrong Credentials!");
			alert.setContentText("You entered a wrong email or password.");
			alert.showAndWait();
		}

		if (loggedInAsCustomer) {
			CustomerDashboardPage.initCustomerDashboard(primaryStage, "", "Search Items in GoGoQuery Store");
		} else if (loggedInAsStaff) {
			StaffHomePage.initStaffHome();
		}

	}

	private static boolean isAlnum(String str) {

		boolean isAlpha = false;
		boolean isNum = false;

		for (char c : str.toCharArray()) {
			if (Character.isLetter(c)) {
				isAlpha = true;
				break;
			}
		}
		for (char c : str.toCharArray()) {
			if (Character.isDigit(c)) {
				isNum = true;
				break;
			}
		}

		return isAlpha && isNum;
	}

	public static boolean isOlderThan17(LocalDate dob) {
		LocalDate today = LocalDate.now();
		Period age = Period.between(dob, today);
		return age.getYears() >= 17;
	}

	public static void handleRegister(Stage primaryStage, String email, String password, String confPass, LocalDate dob,
			Toggle gender, boolean isAgree) {

		String[] splitEmail = email.split("@");
		System.out.println(gender);
		if (email.isEmpty()) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Email must be filled");
			alert.showAndWait();
		} else if (!uniqueUser(email)) {
			Alert alert = Factory.createError("Register Failed", "Register Error",
					"Email is already taken. Please use other address.");
			alert.showAndWait();
		} else if (!isValid(splitEmail[0])) {
			Alert alert = Factory.createError("Register Failed", "Register Error",
					"no special character is allowed other than '@', '_', or '.'");
			alert.showAndWait();
		} else if (!email.endsWith("@gomail.com")) {
			Alert alert = Factory.createError("Register Failed", "Register Error",
					"Email must ends with '@gomail.com'");
			alert.showAndWait();
		} else if (password.isEmpty()) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Password must be filled");
			alert.showAndWait();
		} else if (!isAlnum(password)) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Password must be alphanumeric");
			alert.showAndWait();
		} else if (!password.equals(confPass)) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Passwords don't match");
			alert.showAndWait();
		} else if (dob == null) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Please select date of birth");
			alert.showAndWait();
		} else if (!isOlderThan17(dob)) {
			Alert alert = Factory.createError("Register Failed", "Register Error",
					"User must be at least 17 years old");
			alert.showAndWait();
		} else if (gender == null) {
			Alert alert = Factory.createError("Register Failed", "Register Error", "Select your gender");
			alert.showAndWait();
		} else if (!isAgree) {
			Alert alert = Factory.createError("Register Failed", "Register Error",
					"You must agree to the terms and conditions");
			alert.showAndWait();
		} else {
			String userGender = gender.toString().contains("'Female'") ? "Female" : "Male";
			SelectRolePage.initSelectRolePage(primaryStage, new User(0,
					dob.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), email, password, userGender, "undefined"));
		}

	}

	private static boolean isValid(String string) {
		return string.matches("^[\\w.]+$");
	}

	public static void registerUser(User newUser, String newUserRole) {
		String targetTable = null;
		String entityName = null;

		if (newUserRole.equalsIgnoreCase("Manager")) {
			newUserRole = "Manager";

		} else {
			newUserRole = "Shopper";

		}

		// fk prepared statement
		String query = "INSERT INTO MsUser (UserDOB, UserEmail, UserPassword, UserGender, UserRole) VALUES ('"
				+ newUser.getUserDOB() + "	', '" + newUser.getUserEmail() + "', '" + newUser.getUserPassword() + "', '"
				+ newUser.getUserGender() + "','" + newUserRole + "')";

		db.execUpdate(query);
	}
}
