package cathulhu;

public class CathulhuException extends Exception{

    /**
     * Returns exceptions for invalid Cathulhu inputs
     * @param message String message to be displayed
     */
    public CathulhuException(String message) {
        super(message);
    }
}
