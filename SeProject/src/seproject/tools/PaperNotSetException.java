package seproject.tools;

public class PaperNotSetException extends RuntimeException{

    /**
     * Creates a new instance of <code>PaperNotSetException</code> without
     * detail message.
     */
    public PaperNotSetException() {
    }

    /**
     * Constructs an instance of <code>PaperNotSetException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PaperNotSetException(String msg) {
        super(msg);
    }
}
