package Handlers;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import Entities.DatabaseEntities.TransactionDetail;
import Entities.DatabaseEntities.TransactionHeader;
import Entities.ModifiedEntities.FetchedCart;
import Entities.ModifiedEntities.TransactionInfo;
import Main.Main;
import Util.Connect;

public class TransactionHandler {

	private static Connect db = Connect.getInstance();

	public TransactionHandler() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<TransactionHeader> fetchTransaction() {
		ArrayList<TransactionHeader> tHeaderList = new ArrayList<>();

		String query = "SELECT * FROM TransactionHeader;";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int tID = db.rs.getInt("TransactionID");
				int cID = db.rs.getInt("CustomerID");
				String dateCreated = db.rs.getString("DateCreated");
				String status = db.rs.getString("Status");

				tHeaderList.add(new TransactionHeader(tID, cID, dateCreated, status));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tHeaderList;
	}

	public static ArrayList<TransactionDetail> fetchTransactionDetail() {
		ArrayList<TransactionDetail> tDetailList = new ArrayList<>();

		String query = "SELECT * FROM TransactionDetail;";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int tID = db.rs.getInt("TransactionID");
				int iID = db.rs.getInt("ItemID");
				int qty = db.rs.getInt("Quantity");

				tDetailList.add(new TransactionDetail(tID, iID, qty));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tDetailList;
	}

	public static int getMaxTransactionID() {
		String query = "SELECT MAX(TransactionID) AS max_id FROM TransactionHeader";
		db.rs = db.execQuery(query);
		int maxID = -1;
		try {
			if (db.rs.next()) {
				maxID = db.rs.getInt("max_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maxID;
	}

	public static void addTransaction(int totalPrice, ArrayList<FetchedCart> customerCartList) {
		LocalDate currentDate = LocalDate.now();
		String query = String.format("INSERT INTO TransactionHeader(UserID, DateCreated, Status) VALUES(%d, '%s','%s')",
				Main.currentUser.getUserID(), currentDate, "In Queue");
		db.execUpdate(query);

		for (FetchedCart fetchedCart : customerCartList) {
			query = String.format("INSERT INTO TransactionDetail(TransactionID, ItemID, Quantity) VALUES(%d, %d, %d)",
					getMaxTransactionID(), fetchedCart.getItemID(), fetchedCart.getQuantity());
			db.execUpdate(query);

		}

		CartHandler.clearCustomerCart();

	}

	public static ArrayList<TransactionInfo> fetchTransactionInfo() {
		ArrayList<TransactionInfo> tInfoList = new ArrayList<>();

		String query = "SELECT th.*, mu.UserEmail, SUM(td.Quantity * mi.ItemPrice) AS 'TotalPrice' FROM `transactiondetail` td JOIN transactionheader th ON td.TransactionID = th.TransactionID JOIN msitem mi ON td.ItemID = mi.ItemID JOIN msuser mu on mu.UserID = th.UserID group by TransactionID ORDER BY th.TransactionID DESC;";
		db.rs = db.execQuery(query);
		try {
			while (db.rs.next()) {

				int tID = db.rs.getInt("TransactionID");
				int uID = db.rs.getInt("UserID");
				String uEmail = db.rs.getString("UserEmail");
				String dateCreated = db.rs.getString("DateCreated");
				String status = db.rs.getString("Status");
				Double totalPrice = db.rs.getDouble("TotalPrice");

				DecimalFormat df = new DecimalFormat("#0.00");

				// Format the totalPrice to two decimal places
				String formattedTotalPrice = df.format(totalPrice);

				// Convert the formatted string back to a double if necessary
				Double formattedPrice = Double.parseDouble(formattedTotalPrice);

				tInfoList.add(new TransactionInfo(tID, uID, uEmail, dateCreated, status, formattedPrice));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tInfoList;
	}
}
