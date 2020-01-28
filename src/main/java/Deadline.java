public class Deadline extends Task {
    String due;
    public Deadline(String[] description) {
        super(description[0]);
        due = (description[1].split(" ", 2))[1];
    }

    public Deadline(String description, String dueDate, boolean isDone) {
        super(description, isDone);
        due = dueDate;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + due + ")";
    }

    public String addToFile() {
        return "D | " + super.addToFile() + " | " + due;
    }
}
