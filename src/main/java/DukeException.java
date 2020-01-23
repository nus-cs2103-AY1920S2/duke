public class DukeException extends Exception {

    protected String error;

    public DukeException (String error) {
        super (error);
        this.error = error;
    }

    @Override

    public String toString() {
        return (":( OOPS!!! " + error);
    }

}

