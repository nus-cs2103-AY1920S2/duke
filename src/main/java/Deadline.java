/**
 * Deadline
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 29 Jan 2020
 *
 * @author Jel
 */
public class Deadline extends Task {
    String due;

    Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    protected String getDueDate() {
        return this.due;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)\n", super.toString(), this.due);
    }
}
