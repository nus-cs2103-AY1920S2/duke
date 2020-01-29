public class DukeException extends Exception {
    public DukeError error;

    public DukeException(DukeError error) {
        this.error = error;
    }

    public String toString() {
        return error.getErrorMessage();
    }
}

