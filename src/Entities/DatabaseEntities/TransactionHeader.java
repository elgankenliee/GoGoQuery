package Entities.DatabaseEntities;

public class TransactionHeader {

	private int transactionID;
	private int customerID;
	private String dateCreated;
	private String status;

	public TransactionHeader(int transactionID, int customerID, String dateCreated, String status) {
		super();
		this.transactionID = transactionID;
		this.customerID = customerID;
		this.dateCreated = dateCreated;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public TransactionHeader() {
		// TODO Auto-generated constructor stub
	}

}
