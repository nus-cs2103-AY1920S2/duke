import java.time.LocalDate;

/**
 * This class deals with a todo item.
 */
public class Todo extends Item {
    Todo(String name) {
        super(name, false);
    }

    Todo(String name, boolean done) {
        super(name, done);
    }

    public String toString() {
        String temp = "   [T]" + super.toString() + "\n";
        return temp;
    }

    /**
     * Returns the string before it is marked done,
     * used for string substitution in the txt file when it is marked done.
     */
    public String replace() {
        String temp = "   [T]["+ "\u2718" +"] " + super.getName() + "\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item.
     */
    public String now() {
        return this.toString();
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }
}
