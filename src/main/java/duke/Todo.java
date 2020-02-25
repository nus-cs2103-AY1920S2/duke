package duke;

/**
 * Represents a to-do.
 */
public class Todo extends Task {
    public Todo(String instr) {
        super(instr);
        super.type = "TODO";
    }

    @Override
    public String toString() {
        if (super.isDone) {
            return "[T][" + super.tick + "] " + super.instr;
        } else {
            return "[T][" + super.cross + "] " + super.instr;
        }
    }
}
