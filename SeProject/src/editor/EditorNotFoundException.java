package editor;

public class EditorNotFoundException extends Exception{

    /**
     * Creates a new instance of <code>EditorNotFoundException</code> without
     * detail message.
     */
    public EditorNotFoundException() {
    }

    /**
     * Constructs an instance of <code>EditorNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EditorNotFoundException(String msg) {
        super(msg);
    }
}
