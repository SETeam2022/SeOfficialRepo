package seproject.commands;
/**
 * The base for all the commands, specify all the operation that 
 * a command should do
 */
public interface Command {
        
        public void execute();
        public void undo();
        
}
