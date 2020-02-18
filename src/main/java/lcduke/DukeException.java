package lcduke;

/** Ths creates a DukeException object.
 */
public class DukeException extends Exception {
    /** This is the constructor to create the DukeException Object.
     *
     * @param message Description of user's input.
     */
    protected DukeException(String message) {
        super(message);
    }
}
