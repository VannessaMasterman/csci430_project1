package nessa.process.Admin;

//import ethanlo.*;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
import warehouseInventory.warehouse.*;

/**
	This class performs the "Accept Shipment" business process
*/
public class PAcceptShipment extends UIProcess {
	
	public PAcceptShipment(String category, String name, String description){
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
		// for this one we want to loop over and over. Let's clear the screen as well

		boolean running = true;
		int uniqueProducts = 0;
		int grossProduct = 0;
		while(running){
			ConsoleUtil.clearConsole();
			
			// Print a product adding header (keep running total of # items, total gross quantity of product)
			System.out.println("=======Add Product======");
			System.out.println("========================");
			System.out.println("Unique [" + uniqueProducts + "] | Total [" + grossProduct+"]");
			System.out.println("========================");
			// Prompt for basic info

			System.out.print("Please enter the product id: ");
			String productID = ConsoleUtil.readLine();
			if(Inventory.instance().isNewProduct(productID) == -1){
				// if product is new prompt to register product
				// this function handles the rest
				grossProduct += registerNewProduct(productID);
			}else{

				System.out.print("Supplier Name : ");
				String supplierID = ConsoleUtil.readLine();

				// get extra data for adding existing product
				// Get quantity adding
				String quantityStr = "";
				int qty = 0;
				while(qty == 0){
					System.out.print("How many units of product are you accepting? (integer) :");
					quantityStr = ConsoleUtil.readLine();
					try{
						qty = Integer.parseInt(quantityStr);
					}catch(NumberFormatException nfe){
						System.err.println("Failed to interpret \"" + quantityStr + "\" as an integer. Please try again.");
					}
				}

				// Get purchase price
				quantityStr = "";
				double price = 0.0;
				while(price <= 0.0){
					System.out.print("What is the purchase price per unit? (double) :");
					quantityStr = ConsoleUtil.readLine();
					try{
						price = Double.parseDouble(quantityStr);
					}catch(NumberFormatException nfe){
						System.err.println("Failed to interpret \"" + quantityStr + "\" as a double. Please try again.");
					}
				}


				// Add quantity of product to the inventory

				// if the product exists, the retail price isn't modified, at this point we know the product exists so we don't need to gather info we don't plan on using anyway. Make it easier on the admin
				Inventory.instance().addProduct(productID, supplierID, price, 0.0, qty);
				/*int index = addProductToSupplierInventory(productID, qty, price, supplierID);
				Supplier thisSupplier = SupplierCollection.instance().supplierList.get(index);
				thisSupplier.printProductList();*/
			}			
			
			// ask if user would like to enter another product?
			System.out.print("Would you like to add another product? (Y/N) :");
			String input = ConsoleUtil.readLine();
			// break if user would not like to enter any more products
			uniqueProducts++;
			if(!input.toLowerCase().contains("y")) running = false; // anything besides a yes is a no
		}
		// display a review page
		ConsoleUtil.clearConsole();
		System.out.println("=====Shipment Review====");
		System.out.println("========================");
		System.out.println("Unique products added : " + uniqueProducts);
		System.out.println("Total quantity of product added : " + grossProduct);
		System.out.println("========================");
		System.out.println("Press enter to continue...");
		ConsoleUtil.readLine();
	}

	private int registerNewProduct(String productID){
		System.out.println();
		System.out.println("--Adding Supplier--");
		
		System.out.print("Supplier Name : ");
		String supplierID = ConsoleUtil.readLine();

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
		Inventory.instance().addProduct(productID, supplierID, supplierPrice, retailPrice, quantity);

		return quantity;
	}

	/*private int addProductToSupplierInventory(String productID, int quantity, double supplierPrice, String supplierID) {
		Product e = new Product(productID, quantity, supplierPrice);
		int index = SupplierCollection.instance().findSupplierIndex(supplierID);

		if (index == -1) {
			System.out.print("Supplier Phone Number : ");
			String phone = ConsoleUtil.readLine();
	
			System.out.print("Supplier Address : ");
			String address = ConsoleUtil.readLine();

			SupplierCollection.instance().addSupplier(supplierID, phone, address);
			int newIndex = SupplierCollection.instance().findSupplierIndex(supplierID);
			Supplier newSupplier = SupplierCollection.instance().supplierList.get(newIndex);
			newSupplier.addProductInventory(e);
			return newIndex;
		}

		Supplier thisSupplier = SupplierCollection.instance().supplierList.get(index);

		int count = thisSupplier.getProductInventory().size();

		boolean isPresent = false;

		for (int i = 0; i < count; i++) {
			Product test = thisSupplier.getProductInventory().get(i);
			String b = test.getsupplierID();
			if (b.equalsIgnoreCase(supplierID)) {
				isPresent = true;
				test.addSupplierQuantity(quantity);
				return index;
			} else if ((isPresent == false) && (i == count - 1)) {
				thisSupplier.addProductInventory(e);
				return index;
			}
		}
		return 0;
	}*/
}