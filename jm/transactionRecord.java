/*---TransactionRecord Class
Author: Israel Musa--*/

public class transactionRecord{
	private String date;
	private String clientID;
	private double price;
	
    //Date
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return date;
	}

    //clientID
	public void setVendor(String vendor) {
		this.clientID = clientID;
	}
	public String getClientID() {
		return clientID;
	}

    //Price
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice() {
		return price;
	}
}