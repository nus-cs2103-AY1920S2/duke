package exceptions;

/**
 * Throws EmptySearchException when user does
 * not specify a word to search for.
 *
 * @author ChesterSim
 */
public class EmptySearchException extends Exception {

    public EmptySearchException() {
        super();
    }

    @Override
    public String toString() {
        return ":( OOPS!!! You did not input the word to search for.";
    }
}