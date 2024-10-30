package Handlers;

import java.sql.SQLException;
import java.util.ArrayList;

import Entities.DatabaseEntities.Cart;
import Entities.DatabaseEntities.Item;
import Entities.ModifiedEntities.FetchedCart;
import Factories.Factory;
import Main.Main;
import Util.Connect;
import View.CustomerCartPage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CartHandler {

	private static Connect db = Connect.getInstance();

	public static VBox loadCart(Stage primaryStage, ScrollPane cartPageScrollPane) {
		ArrayList<FetchedCart> customerCartList = fetchCustomerCart(Main.currentUser.getUserID());
		VBox itemContainer = new VBox();
		itemContainer.setSpacing(20);

		HBox resultMsgContainer = new HBox();

		if (customerCartList.size() < 1) {
			CustomerCartPage.cartIsEmpty = true;
			Label emptyCartLabel = new Label("Your cart is empty!");
			emptyCartLabel.setFont(Font.font("Helvetica", FontWeight.NORMAL, FontPosture.ITALIC, 50));
			emptyCartLabel.setTextFill(Color.web("717489"));
			itemContainer.getChildren().add(emptyCartLabel);
		} else {

			CustomerCartPage.cartIsEmpty = false;
			resultMsgContainer.getChildren().addAll(Factory.createSrchMsgLbl("Showing ", "#717489"),
					Factory.createSrchMsgLbl(Integer.toString(customerCartList.size()), Main.themeOrange),
					Factory.createSrchMsgLbl(" products in cart", "#717489"));
			itemContainer.getChildren().add(resultMsgContainer);

			for (FetchedCart cartRow : customerCartList) {
				CustomerCartPage.cartSubtotal += cartRow.getCartItem().getItemPrice() * cartRow.getQuantity();
				itemContainer.getChildren().add(Factory.createCartItemBox(primaryStage, cartRow, cartPageScrollPane));
			}
		}
		return itemContainer;

	}

	public static ArrayList<FetchedCart> fetchCustomerCart(int targetUserID) {
		ArrayList<FetchedCart> customerCartList = new ArrayList<>();

		String query = "SELECT sq.UserID, sq.Quantity, msitem.* FROM ( SELECT * FROM MsCart WHERE UserID = "
				+ targetUserID + " ) as sq JOIN msitem ON sq.ItemID = msitem.ItemID;";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int userID = db.rs.getInt("UserID");
				int itemID = db.rs.getInt("ItemID");
				int quantity = db.rs.getInt("Quantity");
				String itemName = db.rs.getString("ItemName");
				Double itemPrice = db.rs.getDouble("ItemPrice");
				String itemDesc = db.rs.getString("ItemDesc");
				int itemStock = db.rs.getInt("ItemStock");
				String itemCategory = db.rs.getString("ItemCategory");

				customerCartList.add(new FetchedCart(userID, itemID, quantity,
						new Item(itemID, itemName, itemPrice, itemCategory, itemDesc, itemStock)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return customerCartList;
	}

	public static void clearCustomerCart() {
		db.execUpdate("DELETE FROM MsCart WHERE UserID = " + Main.currentUser.getUserID() + ";");
	}

	static ArrayList<Cart> fetchCart() {
		ArrayList<Cart> cartList = new ArrayList<>();

		String query = "SELECT * FROM MsCart WHERE UserID = " + Main.currentUser.getUserID() + ";";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int userID = db.rs.getInt("UserID");
				int itemID = db.rs.getInt("ItemID");
				int quantity = db.rs.getInt("Quantity");
				cartList.add(new Cart(userID, itemID, quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cartList;
	}

	public static void addToCart(Item item, int quantity) {
		ArrayList<Cart> cartList = fetchCart();
		if (cartList.size() < 1) {
//
//			System.out.println(Main.currentUser.getUserID());
//			System.out.println(item.getItemID());
//			System.out.println(quantity);
			insertItem(Main.currentUser.getUserID(), item, quantity);
		} else {
			if (!itemInCart(item.getItemID(), cartList)) {
				insertItem(Main.currentUser.getUserID(), item, quantity);
			} else {
				Cart existingCart = null;
				for (Cart cart : cartList) {
					if (cart.getItemID() == item.getItemID()) {
						existingCart = cart;
					}
				}
				updateItem(existingCart, quantity, item);
			}
		}
	}

	private static void insertItem(int userID, Item item, int quantity) {
		String query = String.format("INSERT INTO MsCart(UserID, ItemID, Quantity) VALUES(%d, %d, %d)", userID,
				item.getItemID(), quantity);
		db.execUpdate(query);

		Alert newAlert = new Alert(AlertType.INFORMATION);
		newAlert.setTitle("Item Added to Cart");
		newAlert.setHeaderText("Item Added Successfully");
		newAlert.setContentText("The item '" + item.getItemName() + "' has been added to your cart with a quantity of "
				+ quantity + " unit(s).");
		newAlert.showAndWait();

	}

	private static void updateItem(Cart existingCart, int quantity, Item selectedItem) {
		Boolean qtyExceedsStock = false;
		int newQuantity = existingCart.getQuantity() + quantity;
		ArrayList<Item> itemList = DashboardHandler.fetchItem();
		for (Item item : itemList) {
			if (item.getItemID() == existingCart.getItemID()) {
				if (newQuantity > item.getItemStock()) {
					newQuantity = item.getItemStock();
					qtyExceedsStock = true;
					break;
				}
			}
		}

		String query = String.format("UPDATE MsCart SET Quantity = %d WHERE ItemID = %d", newQuantity,
				existingCart.getItemID());
		db.execUpdate(query);

		if (qtyExceedsStock) {
			Alert newAlert = new Alert(AlertType.INFORMATION);
			newAlert.setTitle("Quantity Exceeds Stock");
			newAlert.setHeaderText("Not enough stock available");
			newAlert.setContentText("There are " + selectedItem.getItemStock()
					+ " units left in stock for this item, and you already have " + existingCart.getQuantity()
					+ " units in your cart. The quantity in your cart has been adjusted to the maximum available stock.");
			newAlert.showAndWait();
		} else {
			Alert newAlert = new Alert(AlertType.INFORMATION);
			newAlert.setTitle("Item Already in Cart");
			newAlert.setHeaderText("Quantity Updated");
			newAlert.setContentText(
					"'" + selectedItem.getItemName() + "' is already in your cart [" + existingCart.getQuantity()
							+ " unit(s)]. The quantity has been updated to " + newQuantity + " units.");
			newAlert.showAndWait();
		}
	}

	static Boolean itemInCart(int itemID, ArrayList<Cart> cartList) {
		for (Cart cart : cartList) {
			if (cart.getItemID() == itemID) {
				return true;
			}
		}
		return false;
	}

	public static void removeItemFromCart(FetchedCart cartRow) {
		String query = String.format("DELETE FROM MsCart WHERE UserID = %d AND ItemID = %d",
				Main.currentUser.getUserID(), cartRow.getItemID());
		db.execUpdate(query);

	}

	public static void updateItemQuantity(FetchedCart cartRow, int newQuantity) {
		String query = String.format("UPDATE MsCart SET Quantity = %d WHERE ItemID = %d", newQuantity,
				cartRow.getItemID());
		db.execUpdate(query);
	}

	public static void updateSubtotal() {
		ArrayList<FetchedCart> customerCartList = fetchCustomerCart(Main.currentUser.getUserID());
		if (customerCartList.size() > 0) {

			CustomerCartPage.cartSubtotal = 0.0;
			for (FetchedCart fetchedCart : customerCartList) {
				CustomerCartPage.cartSubtotal += fetchedCart.getCartItem().getItemPrice() * fetchedCart.getQuantity();
			}
		}

	}

	public static void createTransaction() {

		ArrayList<FetchedCart> customerCartList = fetchCustomerCart(Main.currentUser.getUserID());
		int totalPrice = 0;
		for (FetchedCart fetchedCart : customerCartList) {
			totalPrice += fetchedCart.getCartItem().getItemPrice() * fetchedCart.getQuantity();

		}
		TransactionHandler.addTransaction(totalPrice, customerCartList);

	}
}
