package duke.pack;

public class DukeException extends Exception {
    String s;

    /**
     * creates an exception specific to Duke
     * @param s description of exception
     */
    public DukeException(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
