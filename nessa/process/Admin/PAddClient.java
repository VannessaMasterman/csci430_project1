package nessa.process.Admin;

import nessa.process.UIProcess;
import nessa.util.ConsoleUtil;

public class PAddClient extends UIProcess {
	
	public PAddClient(String category, String name, String description){
		super(category, name, description);	
	}
	
	@Override
	public void process(int clientID){
		System.out.println("Performing dummy process " + category + ":" + name.replace(" ", "_"));
		ConsoleUtil.sleepForSeconds(1.5f);
	}
}