package dukeApp.files;

public class DukeException extends Throwable{
    protected String task;
    protected String str;

    public DukeException(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
