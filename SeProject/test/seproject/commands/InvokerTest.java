package seproject.commands;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvokerTest {

    private Command testCommand;
    private Invoker inv;

    public InvokerTest() {
    }

    /**
     * This method instances a Command and an Invoker.
     */
    @Before
    public void setUp() {
        testCommand = new Command() {
            @Override
            public void execute() {
                System.out.println("Im a test command executing something");
            }

            @Override
            public void undo() {
                System.out.println("Im a test commnad undoing something");
            }
        };

        inv = Invoker.getInvoker();
    }

    /**
     * Test of executeCommand method, of class Invoker.
     */
    @Test
    public void testExecuteCommand() {
        System.out.println("executeCommand");
        inv.executeCommand(testCommand);
        assertTrue(inv.getUndoIsEnabledProperty().getValue());

    }

    /**
     * Test of undoLastCommand method, of class Invoker.
     */
    @Test
    public void testUndoLastCommand() {
        System.out.println("UndoLastCommand");
        inv.executeCommand(testCommand);
        inv.undoLastCommand();
        assertFalse(inv.getUndoIsEnabledProperty().getValue());
    }

    /**
     * Test of getInvoker method, of class Invoker.
     */
    @Test
    public void testGetInvoker() {
        System.out.println("getInvoker");
        Invoker testInvoker = Invoker.getInvoker();
        assertEquals(inv, testInvoker);
    }

}
