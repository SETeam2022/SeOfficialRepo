/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seproject.commands;

import java.util.Stack;

/**
 *
 * @author teodo
 */
public class Invoker {
    
    private final Stack<Command> stack;
    
    private static Invoker invoker;
    
    
    
    /**
     * This method let the Invoker execute the command passed as a parameter
     * @param com 
     */
    public void executeCommand(Command com){
        stack.push(com);
        com.execute();
    }
    /**
     * This method let the Invoker execute the undo of the last command 
     */
    public void undoLastCommand(){
        Command com = stack.pop();
        com.undo();
    }
    
    private Invoker(){
        stack = new Stack();
    }
    
    /**
     * This method return the only istance of the invoker, this object can be used
     * by all the class that want to perfom an action
     * @return invoker the only istance of the invoker object
     */
    public static Invoker getInvoker(){
        if (invoker == null){
            invoker = new Invoker();
        }
        return invoker;
    }
}
