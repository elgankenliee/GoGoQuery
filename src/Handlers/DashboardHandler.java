package Handlers;

import java.sql.SQLException;
import java.util.ArrayList;

import Entities.DatabaseEntities.Item;
import Factories.Factory;
import Main.Main;
import Util.Connect;
import View.CustomerDashboardPage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardHandler {

	private static Connect db = Connect.getInstance();

	public static ArrayList<Item> fetchItem() {

		CustomerDashboardPage.categoryList.clear();

		ArrayList<Item> itemList = new ArrayList<>();
		String query = "SELECT * FROM MsItem ORDER BY ItemID DESC";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int itemID = db.rs.getInt("ItemID");
				String itemName = db.rs.getString("ItemName");
				Double itemPrice = db.rs.getDouble("ItemPrice");
				String itemDesc = db.rs.getString("ItemDesc");
				int itemStock = db.rs.getInt("ItemStock");
				String itemCategory = db.rs.getString("ItemCategory");
				itemList.add(new Item(itemID, itemName, itemPrice, itemCategory, itemDesc, itemStock));

				if (CustomerDashboardPage.categoryList.size() < 1) {
					CustomerDashboardPage.categoryList.add(itemCategory.toLowerCase());
				} else if (!CustomerDashboardPage.categoryList.contains(itemCategory.toLowerCase())) {
					CustomerDashboardPage.categoryList.add(itemCategory.toLowerCase());
				}

//				itemList.add(new Item(itemID, itemName, itemPrice, itemDesc, itemStock));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for (String str : CustomerDashboardPage.categoryList) {
//			System.out.println(str);
//		}

		return itemList;
	}

	public DashboardHandler() {
		// TODO Auto-generated constructor stub
	}

//	public static VBox handleFilter(Stage primaryStage, Toggle categoryToggle) {
//		
//	}

	public static VBox handleSearch(Stage primaryStage, String searchedString) {
		ArrayList<Item> itemList = fetchItem();
		VBox itemContainer = new VBox();
		itemContainer.setSpacing(20);
		int minBoxHeight = 1500;

		HBox resultMsgContainer = new HBox();

		if (searchedString.isEmpty()) {

			resultMsgContainer.getChildren().addAll(Factory.createSrchMsgLbl("Showing ", "#717489"),
					Factory.createSrchMsgLbl(Integer.toString(itemList.size()), Main.themeOrange),
					Factory.createSrchMsgLbl(" products", "#717489"));
			itemContainer.getChildren().add(resultMsgContainer);

			for (Item item : itemList) {
				minBoxHeight += 200;
				itemContainer.getChildren().add(Factory.createDashboardItemBox(primaryStage, item));
			}
		} else {
			ArrayList<Item> suggestedItems = new ArrayList<>();

			searchedString = searchedString.toLowerCase();
			for (Item item : itemList) {
				if (item.getItemName().toLowerCase().contains(searchedString)
						|| item.getItemCategory().toLowerCase().contains(searchedString)
						|| searchedString.contains(item.getItemName().toLowerCase())
						|| searchedString.contains(item.getItemCategory().toLowerCase())) {
					suggestedItems.add(item);
				}
			}

			resultMsgContainer.getChildren().addAll(Factory.createSrchMsgLbl("Showing ", "#717489"),
					Factory.createSrchMsgLbl(Integer.toString(suggestedItems.size()), Main.themeOrange),
					Factory.createSrchMsgLbl(" products for ", "#717489"),
					Factory.createSrchMsgLbl("'" + searchedString + "'", Main.themeOrange));
			itemContainer.getChildren().add(resultMsgContainer);

			for (Item item : suggestedItems) {
				itemContainer.getChildren().add(Factory.createDashboardItemBox(primaryStage, item));
			}
			if (suggestedItems.size() > 5) {
				minBoxHeight += (suggestedItems.size() - 5) * 170;
			}

		}
		Main.tempScreenMinHeight = minBoxHeight;
		return itemContainer;
	}
}
