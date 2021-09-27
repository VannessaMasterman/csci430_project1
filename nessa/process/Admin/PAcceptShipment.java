package nessa.process.Admin;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

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
		System.out.println("Performing dummy process " + category + ":" + name.replace(" ", "_"));
		ConsoleUtil.sleepForSeconds(1.5f);
	}
}