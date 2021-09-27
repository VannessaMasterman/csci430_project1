package nessa;

import java.util.*;
import nessa.util.ConsoleUtil;
import nessa.process.*;
import nessa.process.Admin.*;
import nessa.process.Client.*;
//import java.lang.*;

public class UserInterface {
	
	//private int previousCommand;
	private Map<String, List<UIProcess>> currentProcesses;
	private static Map<String, List<UIProcess>> AdminProcesses;
	private static Map<String, List<UIProcess>> ClientProcesses;
	private boolean isRunning = true;
	private String userType = "-";
	private int clientID = -1;
	
	/**
		Entry point. Instantiates the UserInterface class and calls the start method on that instance
		UserInterface could be treated as a singleton, but there is no need for that added complexity.
	*/
	public static void main(String[] args){
		UserInterface ui = new UserInterface();
		ui.start();
	}
	
	/**
		Constructor for UserInterface: initializes the list of the processes so we can swap between client accessible functions and admin accessible functions. The processes each need access to are mutually exclusive, but the same process could be added to each list should a new process be introduced that both client and admin require access to
	*/
	public UserInterface(){
		// Add Client Processes
		ClientProcesses = new HashMap<String, List<UIProcess>>();
		addProcessClient(new PViewBySupplier("product", "View By Supplier", "View a list of products from a specific supplier"));
		addProcessClient(new PViewProduct("product", "View Products", "View a list of all products"));
		addProcessClient(new PViewSupplier("product", "View Suppliers", "View a list of all suppliers"));
		
		addProcessClient(new PViewCart("cart", "View Cart", "View the products in your cart"));
		addProcessClient(new PAddProductToCart("cart", "Add", "Add a product to your cart"));
		addProcessClient(new PRemoveProductFromCart("cart", "Remove", "Remove a product from your cart"));
		addProcessClient(new PClearCart("cart", "Clear", "Remove all products from your current cart"));
		addProcessClient(new PProcessCart("cart", "Process", "Process your current cart"));
		
		addProcessClient(new PQueryTransactionClient("client", "Query Transaction", "View your transactions"));
		

		// Add Admin Processes
		AdminProcesses = new HashMap<String, List<UIProcess>>();
		addProcessAdmin(new PAddClient("add", "Add Client", "Add a new client to the system"));
		addProcessAdmin(new PAddProduct("add", "Add Product", "Add a new product to the system"));
		addProcessAdmin(new PAddSupplier("add", "Add Supplier", "Add a supplier to the system"));
		addProcessAdmin(new PViewTransactionsAdmin("client", "View Transactions", "View a list of client transactions"));
		addProcessAdmin(new PViewBalances("client", "View Balances", "View the balances on client accounts"));
		addProcessAdmin(new PAcceptShipment("inventory", "Accept Shipment", "Accept an incoming shipment"));
		addProcessAdmin(new PQueryInventory("inventory", "Query", "Query the inventory"));
		
<<<<<<< Updated upstream
		addProcessAdmin(new PPrintClientList("debug", "View Client List", "prints out the list of registered clients"));
		addProcessAdmin(new PPrintSupplierList("debug", "View Supplier List", "prints out the list of registered suppliers"));
=======
		addProcessAdmin(new PPrintClientList("debug", "View Client List", "prints the list of registered clients"));
		addProcessAdmin(new PPrintClientList("debug", "View Supplier List", "prints the list of registered suppliers"));
>>>>>>> Stashed changes
	}
	/**
		A helper function that adds a UIProcess to the list of client accessible processes
		@param process the instance of a UIProcess that should be registered
	*/
	private void addProcessClient(UIProcess process){
		String category = process.getCategory();
		List<UIProcess> agg = ClientProcesses.get(category);
		if(agg == null) agg = new ArrayList<UIProcess>();
		agg.add(process);
		ClientProcesses.put(category, agg);
	}
	/**
		A helper function that adds a UIProcess to the list of admin accessible processes
		@param process the instance of a UIProcess that should be registered
	*/
	private void addProcessAdmin(UIProcess process){
		String category = process.getCategory();
		List<UIProcess> agg = AdminProcesses.get(category);
		if(agg == null) agg = new ArrayList<UIProcess>();
		agg.add(process);
		AdminProcesses.put(category, agg);
	}
	
	/**
		A helper function that displays the menu for slecting from the various categories of functions available.
		@param processMap the currently available processes, a Map with the key being a string of the category name
		@returns a List<UIProcess> of the processes in the selected category, or null if none are selected.
	*/
	private List<UIProcess> displayMenuSelect(Map<String, List<UIProcess>> processMap){
		ConsoleUtil.clearConsole();
		displayHeader();
		System.out.println("==Available Categories==");
		System.out.println("========================");
		Set<String> keys = processMap.keySet();
		Object[] arrKeys =  keys.toArray();
		for ( int i = 0; i < arrKeys.length; i++){
			String key = (String)arrKeys[i];
			System.out.println("["+i+"] : " + key);
		}
		System.out.println("========================");
		System.out.println("'q' to exit menu");
		System.out.print("Please enter an integer key : ");
		String input = ConsoleUtil.readLine();
		
		if(input.toLowerCase().contains("q")) return new ArrayList<UIProcess>(); // q to escape anything
		
		int i = Integer.parseInt(input);
		if( i >= 0 && i < arrKeys.length){
			// valid key:
			List<UIProcess> procList = processMap.get((String)arrKeys[i]);
			return procList; // selected list
		}else{
			System.err.println("Key #" + i + " was not valid for 0-" + arrKeys.length);
			return null; // no selection
		}
		
	}
	/**
		Displays the currently selected category of processes. 
		@param the list of processes to display
		@returns the UIProcess that is selected, or null if none is selected.
	*/
	private UIProcess displayCurrentMenu(List<UIProcess> processes){
		ConsoleUtil.clearConsole();
		displayHeader();
		System.out.println("==Available Processes==");
		System.out.println("========================");
		for ( int i = 0; i < processes.size(); i++){
			UIProcess proc = processes.get(i);
			System.out.println("["+i+"] "+proc.getName() + " : " + proc.getDescription());			
		}

		System.out.println("========================");
		System.out.println("'q' to exit menu");
		System.out.print("Please enter an integer key : ");
		String input = ConsoleUtil.readLine();
		if(input.toLowerCase().contains("q")) return null; // q to escape anything

		int i = Integer.parseInt(input);
		if( i >= 0 && i < processes.size()){
			// valid key:
			UIProcess proc = processes.get(i);
			return proc; // selected process
		}else{
			System.err.println("Key #" + i + " was not valid for 0-" + processes.size());
			return null; // no selection
		}
	}
	
	/**
		Displays a prompt for the user to login as an administrator or as a client. The client login requests a client id to be entered.
		@returns the integer client ID, 1 if logged in as an admin or -1 if the login failed
	*/
	private int displayUserLogin(){
		ConsoleUtil.clearConsole();
		displayHeader();
		System.out.println("======Login Options=====");
		System.out.println("========================");
		System.out.println("[0] Login as client");
		System.out.println("[1] Login as admin");
		System.out.println("========================");
		System.out.println("'q' to exit");
		System.out.print("Please enter an integer key : ");
		String input = ConsoleUtil.readLine();
		if(input.toLowerCase().contains("q")) return -1; // q to escape anything
		try{
			int i = Integer.parseInt(input);
			if (i != 0 && i != 1) return -1;
			if(i == 0){
				// get client id
				System.out.print("Please enter your user ID or -1 if you do not have an id \n\t (integer): ");
				input = ConsoleUtil.readLine();
				int id = Integer.parseInt(input);
				clientID = (id == -1) ? getNewClientID() : id;				
			}
			return i;
		}catch(Exception e){
			// user did not enter an integer or a quit character
			return -1;
		}
	}
	
	/**
		The entry point for the user interface. This function handles the broad life cycle of the program.
	*/
	public void start(){
		startupScreen();
		ConsoleUtil.sleepForSeconds(0.5f);
		int user = displayUserLogin();
		if(user != -1){ // -1 skips the entire program
			if (user == 0){
				currentProcesses = ClientProcesses;
				userType = "Client";
			}else{
				currentProcesses = AdminProcesses;				
				userType = "Admin";
			}
			update();
		}
		closingScreen();
		ConsoleUtil.sleepForSeconds(0.05f);
		ConsoleUtil.clearConsole();
		System.exit(0);
	}
	
	/**
		The update function runs a loop until the program deems it appropriate to terminate. Then it yields the thread to the method where it was called.
	*/
	private void update(){
		List<UIProcess> currentMenu = null;
		while(isRunning){
			ConsoleUtil.clearConsole();
			displayHeader();
			System.out.println();
			if(currentMenu == null) {
				currentMenu = displayMenuSelect(currentProcesses);
				if(currentMenu.isEmpty()) isRunning = false;
			}else{
				UIProcess uiProc = displayCurrentMenu(currentMenu);
				if(uiProc != null){
					uiProc.process(clientID);
				} else {
					currentMenu = null;
				}
			}
		}
	}
	
	/**
		A temporary function for displaying a little opening screen.
		This can be edited to allow for a different opening.
	*/
	private void startupScreen(){
		ConsoleUtil.clearConsole();
		System.out.println("This is the startup screen! YAY");
	}
	
	/**
		A function that handles the closing screen. For now it just clears the console.
	*/
	private void closingScreen(){
		ConsoleUtil.clearConsole();
	}
	
	/**
		This prints a header to the console. This header is used in the different screens to create a sense of continuity 
	*/
	private void displayHeader(){
		System.out.println("--Warehouse Management System #13--");
		System.out.println("--"+userType + (clientID >= 0 ? (":"+clientID) : "")+"--");
		System.out.println("--\"We scare because we care.\"--");
	}
	
	/**
		For now this is used to generate a client ID. Which just gets 0. Soon this will actually do something
	*/
	private int getNewClientID(){
		// TODO make this get new ids
		return 0;
	}
	
	
}