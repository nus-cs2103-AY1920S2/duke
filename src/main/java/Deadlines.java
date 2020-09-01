public class Deadlines extends Task {
    String due_date;
    public Deadlines(String description, String due_date) {
        super(description);
        this.due_date = due_date;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + due_date + ")";
    }
}
