package nessa.process.Admin;

import ethanlo.ClientCollection;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
	This class performs the "Add Client" business process
*/
public class PAddClient extends UIProcess {
	
	public PAddClient(String category, String name, String description){
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
		System.out.println("--Adding Client--");
		
		System.out.print("Client Name : ");
		String name = ConsoleUtil.readLine();

		System.out.print("Client Phone Number : ");
		String phone = ConsoleUtil.readLine();

		System.out.print("Client Address : ");
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
		ClientCollection.instance().addClient(name, phone, address);
		// inform user client added
		System.out.println("Client \"" + name + "\" has been added successfully");
		// wait on message for 0.2 seconds so user can read briefly
		ConsoleUtil.sleepForSeconds(0.2f);
	}
}