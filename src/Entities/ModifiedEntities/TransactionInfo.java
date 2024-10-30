package Entities.ModifiedEntities;

public class TransactionInfo {

	private int transactionID;
	private int customerID;
	private String customerEmail;

	private String dateCreated;
	private String status;
	private Double totalPrice;

	public TransactionInfo(int transactionID, int customerID, String customerEmail, String dateCreated, String status,
			Double totalPrice) {
		super();
		this.transactionID = transactionID;
		this.customerID = customerID;
		this.customerEmail = customerEmail;
		this.dateCreated = dateCreated;
		this.status = status;
		this.totalPrice = totalPrice;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}