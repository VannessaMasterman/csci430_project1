package fsm.states.client.processes;

import display.DisplayManager;
import fsm.FSMManager;
import nessa.process.UIProcess;

public class ProcessModifyShoppingCart extends UIProcess {

    public ProcessModifyShoppingCart() {
        super("TBD", "Modify Shopping Cart", "add, remove, or change quantity of items in your cart");
    }

    @Override
    public void process() {
        // TODO Auto-generated method stub
        DisplayManager d = FSMManager.display;
        d.displayMessage("TEMP", true);

    }
    
}
