public class Deadline extends Task {
    private String deadline;

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toSaveString() {
        //D0Fnish project@June 6
        return "D" +
                (isDone ? "1" : "0") +
                name + "@" +
                deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
