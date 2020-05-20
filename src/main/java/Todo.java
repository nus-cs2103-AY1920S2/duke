import java.time.LocalDate;

/**
 * This class deals with a todo item.
 */
public class Todo extends Item {
    Todo(String name) {
        super(name, false);
    }

    Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String toString() {
        String temp = "   [T]" + super.toString() + "\n";
        return temp;
    }

    /**
     * Returns the string before it is marked done,
     * used for string substitution in the txt file when it is marked done.
     */
    public String tobeReplaced() {
        String temp = "   [T][" + 0 + "] " + super.getName() + "\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item with marked as not done.
     */
    public String currentString() {
        String temp = "   [T][" + 1 + "] " + super.getName() + "\n";
        return temp;
    }

    /**
     * Returns the string corresponds to the current item.
     */
    public String checkString() {
        String temp = "   [T][";
        if (super.getDone()) {
            temp += "0";
        } else {
            temp += "1";
        }
        temp += "] " + super.getName();
        return temp;
    }


    public LocalDate getDate() {
        return LocalDate.now();
    }
}
