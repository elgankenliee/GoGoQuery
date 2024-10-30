package Entities.DatabaseEntities;

public class TransactionDetail {
	private int transactionID;
	private int itemID;
	private int quantity;

	public TransactionDetail(int transactionID, int itemID, int quantity) {
		super();
		this.transactionID = transactionID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
