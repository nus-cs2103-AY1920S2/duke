import java.util.function.Predicate;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public static void throwIf(boolean condition, String message) throws DukeException {
        if (!condition) {
            throw new DukeException(message);
        }
    }
}
