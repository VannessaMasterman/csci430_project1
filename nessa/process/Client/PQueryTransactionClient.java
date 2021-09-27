package nessa.process.Client;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
	This class performs the "Query Transactions - Client" business process
*/
public class PQueryTransactionClient extends UIProcess {
	
	public PQueryTransactionClient(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(int clientID){
		System.out.println("Performing dummy process " + category + ":" + name.replace(" ", "_"));
		ConsoleUtil.sleepForSeconds(1.5f);
	}
}