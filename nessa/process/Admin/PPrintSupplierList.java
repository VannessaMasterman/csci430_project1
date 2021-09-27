package nessa.process.Admin;

import ethanlo.SupplierCollection;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

public class PPrintSupplierList extends UIProcess {
	
	public PPrintSupplierList(String category, String name, String description){
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
		System.out.println("--Viewing Supplier List--");
		SupplierCollection.instance().printSupplierList();
		System.out.println("--Press enter to exit--");
        ConsoleUtil.readLine(); // blocks until input, but we just need the enter press

	}
}