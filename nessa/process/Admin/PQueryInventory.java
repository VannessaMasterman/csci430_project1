package nessa.process.Admin;

import fsm.Context;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;
import warehouseInventory.warehouse.Inventory;

/**
	This class performs the "Query Inventory" business process
*/
public class PQueryInventory extends UIProcess {
	
	public PQueryInventory(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		Because this process is an admin only process, it should fail when given a vaild customer ID.
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(){
		int clientID = Context.get().clientID;
		if(clientID != -1) return;
		System.out.println();
		// header
		System.out.println("--Inventory--");

		// print out inventory
		Inventory.instance().printCompleteInventory(); 

		// enter to exit
		System.out.println("Press Enter to close:");
		ConsoleUtil.readLine();
	}
}