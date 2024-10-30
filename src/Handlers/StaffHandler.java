package Handlers;

import Factories.Factory;
import Util.Connect;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StaffHandler {

	private static Connect db = Connect.getInstance();

	static Alert alert;

	public static void handleAddItem(TextField nameField, TextArea descArea, TextField categoryField,
			TextField priceField, Spinner<Integer> quantitySpinner) {
		String errorType = "Insert error!";
		String name = nameField.getText().trim();
		String desc = descArea.getText().trim();
		String category = categoryField.getText().trim();
		String priceText = priceField.getText().trim();
		int quantity = quantitySpinner.getValue();
		if (name.isEmpty() || desc.isEmpty() || category.isEmpty() || priceText.isEmpty()) {
			alert = Factory.createError("Error", errorType, "All fields must be filled out.");
			alert.showAndWait();
			return;
		}

		else if (name.length() < 5 || name.length() > 70) {
			alert = Factory.createError("Error", errorType, "Item name must be between 5 and 70 characters.");
			alert.showAndWait();
			return;
		}

		else if (desc.length() < 10 || desc.length() > 255) {
			alert = Factory.createError("Error", errorType, "Item description must be between 10 and 255 characters.");
			alert.showAndWait();
			return;
		}

		double price = -1.2;
		try {
			price = Double.parseDouble(priceText);
		} catch (NumberFormatException e) {
			alert = Factory.createError("Error", errorType, "Item price must be a valid number.");
			alert.showAndWait();
			return;
		}

		if (price < 0.5 || price > 900000) {
			alert = Factory.createError("Error", errorType, "Item price must be between $0.50 and $900,000.");
			alert.showAndWait();
			return;
		}

		else if (quantity < 1 || quantity > 300) {
			alert = Factory.createError("Error", errorType, "Quantity must be between 1 and 300.");
			alert.showAndWait();
			return;
		}

		insertItem(name, desc, category, price, quantity);

		// Proceed with form submission
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText("Insert Success!");
		alert.setContentText("Item added to product catalog");
		alert.showAndWait();
	}

	private static void insertItem(String name, String desc, String category, Double price, int quantity) {
		// Convert priceText to a double (assuming it's a string representation of the
		// price)

		// Construct the query using string formatting
		String query = String.format("INSERT INTO MsItem (ItemName, ItemDesc, ItemCategory, ItemPrice, ItemStock) "
				+ "VALUES ('%s', '%s', '%s', %.2f, %d)", name, desc, category, price, quantity);

		// Assuming db is your database connection object with execUpdate method
		db.execUpdate(query);

	}

}
