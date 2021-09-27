package nessa.process.Admin;

import ethanlo.ClientCollection;
import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

/**
	This is merely a placeholder UIProcess so that menus can be padded out for visual testing
*/
public class PPrintClientList extends UIProcess {
	
	/**
		The constructor allows for all fields to be defined at point of creation.
	*/
	public PPrintClientList(String category, String name, String description){
		super(category, name, description);	
	}
	
	/**
		The process function for this Dummy Process prints out a string of text to to console and pauses for a brief second before releasing back to the menu.
		Again, this is a placeholder so that the functionality can be tested without needing to call any other module functions
	*/
	@Override
	public void process(int clientID){
		if(clientID != -1) return;
		System.out.println();
		// header
		System.out.println("--Clients--");

		// print out client list
		ClientCollection.instance().printClientList();

		// enter to exit
		System.out.println("Press Enter to close:");
		ConsoleUtil.readLine();
	}
}