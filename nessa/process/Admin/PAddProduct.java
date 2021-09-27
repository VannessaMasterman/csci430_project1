package nessa.process.Admin;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
import warehouseInventory.warehouse.*;
import ethanlo.SupplierCollection;

/**
	This class performs the "Add Product" business process
*/
public class PAddProduct extends UIProcess {
	
	public PAddProduct(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		Because this process is an admin only process, it should fail when given a vaild customer ID.
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(int clientID){
		if(clientID != -1) return;

		System.out.println();
		System.out.println("--Adding Supplier--");
		
		System.out.print("Supplier Name : ");
		String supplierID = ConsoleUtil.readLine();

		System.out.println();
		System.out.println("--Adding Product--");
		
		System.out.print("Product Name : ");
		String productID = ConsoleUtil.readLine();

		System.out.println();
		System.out.println("--Adding Quantity--");
		
		System.out.print("Total Quantity : ");
		String temp = ConsoleUtil.readLine();
		int quantity = Integer.parseInt(temp);

		System.out.println();
		System.out.println("--Adding Supplier Price--");
		
		System.out.print("Supplier Price : ");
		temp = ConsoleUtil.readLine();
		double supplierPrice = Double.parseDouble(temp);

		System.out.println();
		System.out.println("--Adding Retail Price--");
		
		System.out.print("Retail Price : ");
		temp = ConsoleUtil.readLine();
		double retailPrice = Double.parseDouble(temp);

		System.out.println("--Adding Product To Inventory--");

		// The product is added to the warehouse inventory under the Supplier ID
		Inventory.instance().addProduct(productID, supplierID, supplierPrice, retailPrice, quantity);

		// The product is added to the Supplier Product ArrayList
		Product e = new Product(supplierID,quantity,supplierPrice);
		SupplierCollection.instance().suppliertInventory.add(e);


		System.out.println();
		System.out.println("--Product Added to Inventory--");

		ConsoleUtil.sleepForSeconds(1.5f);
	}
}