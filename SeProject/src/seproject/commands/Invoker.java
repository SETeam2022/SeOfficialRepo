package seproject.commands;

import java.util.Stack;
import javafx.beans.property.SimpleBooleanProperty;

public class Invoker {
    
    private final Stack<Command> stack;
    
    private static Invoker invoker = null;
    
    private final SimpleBooleanProperty undoIsEnabled;
    
    
    /**
     * This method let the Invoker execute the command passed as a parameter
     * @param com the command object that represent the action that must be executed 
     */
    public void executeCommand(Command com){
        stack.push(com);
        com.execute();
        undoIsEnabled.setValue(true);
    }
    /**
     * This method let the Invoker execute the undo of the last command 
     */
    public void undoLastCommand(){
        if (!stack.isEmpty()){
            Command com = stack.pop();
            com.undo();
            if (stack.isEmpty()){
                undoIsEnabled.setValue(false);
            }
        }
    }
    
    private Invoker(){
        stack = new Stack<>();
        undoIsEnabled = new SimpleBooleanProperty();
        undoIsEnabled.setValue(false);
    }
    
    /**
     * This method return the only istance of the invoker, this object can be used
     * by all the class that want to perfom an action
     * @return invoker the only istance of the invoker object
     */
    public static Invoker getInvoker(){
        if (Invoker.invoker == null){
            Invoker.invoker = new Invoker();
        }
        return invoker;
    }
    
    public SimpleBooleanProperty getUndoIsEnabledProperty(){
        return invoker.undoIsEnabled;
    }
}
