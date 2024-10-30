package Entities.DatabaseEntities;

public class Cart {

	private int customerID;
	private int itemID;
	private int quantity;

	public Cart(int customerID, int itemID, int quantity) {
		super();
		this.customerID = customerID;
		this.itemID = itemID;
		this.quantity = quantity;
	}

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
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
