package fsm.states.client.processes;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessClientViewProducts extends UIProcess {

    public ProcessClientViewProducts() {
        super("TBD", "View Products", "view available products and their prices");
    }

    @Override
    public void process() {
        // TODO Auto-generated method stub
        DisplayManager d = FSMManager.display;
        d.displayMessage("TEMP", true);

    }
    
}
