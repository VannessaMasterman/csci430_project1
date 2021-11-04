/*---Cart Class
Author: Israel Musa--*/

import java.util.ArrayList;
import java.util.ListIterator;
public class cart {
	ArrayList<Product> product;
	double totalAmount;
	double chargeAmount;
	double tax;
    
	cart() {
		this.product = new ArrayList<Product>();
		this.totalAmount = 0;
		this.chargeAmount = 0;
		this.tax = 0;
	}
	public void addToCart(Product product) {
		this.product.add(product);
	}
	public void showCart() {
		ListIterator<Product> iterator = product.listIterator();
		while(iterator.hasNext()) {
			Product product1 = iterator.next();
			System.out.println(product1);
		}
	}
	public void removeFromCart(Product i) {
		ListIterator<Product> iterator1 = product.listIterator();
		while(iterator1.hasNext()) {
			Product product2 = iterator1.next();
			if (product2.getProductName().equals(i.getProductName())) {
				this.product.remove(i);
				break;
			}
		}
	}
	public double getTotalAmount() {
		ListIterator<Product> iterator2 = product.listIterator();
		this.totalAmount = 0;
		while(iterator2.hasNext()) {
			Product product3 = iterator2.next();
			this.totalAmount = this.totalAmount + (product3.getUnitPrice() * product3.getQuantity());
		}
		return this.totalAmount;
	}
	public double getChargeAmount() {
		this.chargeAmount = 0;
		this.tax = this.totalAmount * (0.14);
		this.chargeAmount = this.totalAmount + this.tax;
		return this.chargeAmount;
	}
	public void printInvoice() {
		ListIterator<Product> iterator3 = product.listIterator();
		while(iterator3.hasNext()) {
			Product product4 = iterator3.next();
			System.out.print(product4.getProductName() + "\t");
			System.out.print(product4.getQuantity() + "\t");
			System.out.print(product4.getUnitPrice() + "\t");
			System.out.println(product4.getUnitPrice() * product4.getQuantity());
		}
		System.out.println("\t\t\t" + "Total: " + this.getTotalAmount());
		this.getPayableAmount();
		System.out.println("\t\t\t" + "Tax  : " + this.tax);
		System.out.println("\t\t\t" + "Total: " + this.getChargeAmount());
	}
}