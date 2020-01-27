public class DukeException extends Exception {

    protected String error;

    /**
     * Constructor for the DukeException Class.
     *
     * @param error Description of Error.
     */
    public DukeException (String error) {
        super (error);
        this.error = error;
    }

    @Override

    public String toString() {
        return (":( OOPS!!! " + error);
    }

}

