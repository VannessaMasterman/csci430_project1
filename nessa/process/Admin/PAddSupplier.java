package nessa.process.Admin;

import ethanlo.SupplierCollection;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
	This class performs the "Add Supplier" business process
*/
public class PAddSupplier extends UIProcess {
	
	public PAddSupplier(String category, String name, String description){
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
		String name = ConsoleUtil.readLine();

		System.out.print("Supplier Phone Number : ");
		String phone = ConsoleUtil.readLine();

		System.out.print("Supplier Address : ");
		String address = ConsoleUtil.readLine();

		System.out.print("Verify the above information is correct (Y/N) : ");
		String verify = ConsoleUtil.readLine();
		if(!verify.toLowerCase().contains("y")){
			// incorrect info, loop until correct
			process(clientID);
			return; // don't continue for any recursive call	
		}
		// verified correct info
		// add client
		SupplierCollection.instance().addSupplier(name, phone, address);
		// inform user client added
		System.out.println("Supplier \"" + name + "\" has been added successfully");
		// wait on message for 0.2 seconds so user can read briefly
		ConsoleUtil.sleepForSeconds(0.2f);
	}
}