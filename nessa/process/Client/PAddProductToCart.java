package nessa.process.Client;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
This class performs the "Add Product To Cart" business process\
*/
public class PAddProductToCart extends UIProcess {
	
	public PAddProductToCart(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		This function is the common inherited function for all of the process classes
		This function is called when the user selects it from the menu
		@param clientID the ID for the client logged in, else -1
	*/
	@Override
	public void process(int clientID){
		// Perform a visual process so we know the debug is working
		System.out.println("Performing dummy process " + category + ":" + name.replace(" ", "_"));
		// Wait for 1.5 seconds so the user can see the action performed. If this thread is blocked, the UI will simply wait
		ConsoleUtil.sleepForSeconds(1.5f);
		// After the thread sleeps we return to the main running loop which will load up whatever menu the user was previously at
	}
}