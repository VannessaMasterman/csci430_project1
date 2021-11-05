package fsm.states.client.processes;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessDisplayClientTransactions extends UIProcess {

    public ProcessDisplayClientTransactions() {
        super("TBD", "View Transactions", "view your previous transactions");
    }

    @Override
    public void process() {
        // TODO Auto-generated method stub
        DisplayManager d = FSMManager.display;
        d.displayMessage("Unfortunately this process is not currently implemented", true);
    }
    
}
