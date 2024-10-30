package Entities.DatabaseEntities;

public class Item {

	private int itemID;
	private String itemName;
	private Double itemPrice;
	private String itemCategory;
	private String itemDesc;
	private int itemStock;

	public Item(int itemID, String itemName, Double itemPrice, String itemCategory, String itemDesc, int itemStock) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemDesc = itemDesc;
		this.itemStock = itemStock;
	}

//	public Item(int itemID, String itemName, int itemPrice, String itemDesc, int itemStock) {
//		super();
//		this.itemID = itemID;
//		this.itemName = itemName;
//		this.itemPrice = itemPrice;
//		this.itemDesc = itemDesc;
//		this.itemStock = itemStock;
//	}

	public int getItemID() {
		return itemID;
	}

	public void setID(int itemID) {
		this.itemID = itemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemStock() {
		return itemStock;
	}

	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}

}
