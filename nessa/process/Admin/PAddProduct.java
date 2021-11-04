package nessa.process.Admin;

import display.DisplayManager;
import fsm.Context;
import fsm.FSMManager;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
import warehouseInventory.warehouse.*;
//import ethanlo.*;

/**
 * This class performs the "Add Product" business process
 */
public class PAddProduct extends UIProcess {

	public PAddProduct(String category, String name, String description) {
		super(category, name, description);
	}

	/**
	 * This function is the common inherited function for all of the process classes
	 * This function is called when the user selects it from the menu Because this
	 * process is an admin only process, it should fail when given a vaild customer
	 * ID.
	 * 
	 * @param clientID the ID for the client logged in, else -1
	 */
	@Override
	public void process() {
		DisplayManager d = FSMManager.display;
		d.setHeader("Adding Product");
		d.displayMessage("You will need to provide specific information related to the product you are adding. Please have this information ready", false);
		// get product id
		String productID = d.getInputString("Please enter the product id: ", true);

		// check if new product
		int productIndex = Inventory.instance().isNewProduct(productID);

		// Allocate vars
		String supplierID = "";
		double supplierPrice = 0;
		double retailPrice = 0;
		int quantity = 0;

		//	> new product: get fresh data
		if (productIndex == -1){
			// it is a new product, get all data
			supplierID = d.getInputString("Please enter the supplier id: ", false);
			supplierPrice = d.getInputDouble("Please enter the supplier price: $");
			retailPrice = d.getInputDouble("Please enter the retail price: $");
			quantity = d.getInputInteger("Please enter the quantity added (integer): ");
		}else{
			// existing product, collect data from Inventory
			// TODO how to get product info using product index?
			supplierID = "dummy";
			supplierPrice = 0.0;
			retailPrice = 0.0;
			quantity = 1;
		}

		// display verification
		d.displayLargeMessage(new String[]{
			"productID = " + productID,
			"supplierID = " + supplierID,
			"supplierPrice = $" + supplierPrice,
			"retailPrice = $" + retailPrice,
			"quantity = " + quantity
		}, false);

		if (! d.verify("Is this information correct?")) {
			// recurse until values are correct
			this.process();
			return;
		}

		// add product to inventory 
		Inventory.instance().addProduct(productID, supplierID, supplierPrice, retailPrice, quantity);

	}
}