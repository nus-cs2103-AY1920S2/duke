public class DukeException extends Exception {

    public String detail;

    public DukeException(String detail) {
        super(detail);

        this.detail = detail;
    }

    @Override
    public String toString() {
        if(!detail.equals("")) {
            return "--------------------------------------------------\n" +
                    ":( Why you so dumb? The description of " + detail + " cannot be empty! SMH \n" +
                    "--------------------------------------------------\n";
        } else {
            return "--------------------------------------------------\n" +
                    ":( Nani? What talking you? \n" +
                    "--------------------------------------------------\n";
        }
    }
}
