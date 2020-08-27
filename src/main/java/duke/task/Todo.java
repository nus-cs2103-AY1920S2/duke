package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns atodo task in string format with a date.
     * @return string format to save in storage.
     */
    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }

    /**
     * Returns atodo task in string format.
     * @return string format to show user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
