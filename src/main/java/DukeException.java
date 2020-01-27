public class DukeException extends Exception {

    protected DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return UI.wrapper(String.format("%s", getMessage()));
    }

}
