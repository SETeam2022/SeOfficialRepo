package seproject.commands;

/**
 * The base for all the commands, specify all the operation that a command
 * should do.
 */
public interface Command {

    /**
     * This method will execute the command.
     */
    public void execute();

    /**
     * This method will do the undo of the command.
     */
    public void undo();

}
