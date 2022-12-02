package seproject.commands;

import java.util.Stack;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The aim of tis class is the execution of all the commands, a command object
 * should never be executed by any other object, the only invoker istance is the
 * only one that can correctly execute all the command and undo all the operation 
 * done in the correct order.
 */
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
     * @return the only istance of the invoker object
     */
    public static Invoker getInvoker(){
        if (Invoker.invoker == null){
            Invoker.invoker = new Invoker();
        }
        return invoker;
    }
    
    /**
     * 
     * @return a property that allaw other objects knowing if the invoker can do the
     * undo of a command or there isn't any command on witch it can work
     */
    public SimpleBooleanProperty getUndoIsEnabledProperty(){
        return invoker.undoIsEnabled;
    }
}
