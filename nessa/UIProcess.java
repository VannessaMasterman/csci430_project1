package nessa.process;

import java.io.*;

public abstract class UIProcess {
	
	protected final String category;
	public final String name;
	public final String description;

	public UIProcess(String category, String name, String description){
		// setup final variables
		this.category = category;
		this.name = name;
		this.description = description;
	}
	
	public String getCategory(){ return category; }
	public String getName(){ return name; }
	public String getDescription(){ return description; }
	
	// - - - - - - - - - - - - - - - - - - - - - -
	//	Process Specific Implementations
	// - - - - - - - - - - - - - - - - - - - - - -
	public abstract void process(int clientID);
	
}