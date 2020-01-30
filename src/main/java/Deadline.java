public class Deadline extends Task{
    private String time;

    public Deadline(String description, String time) {
        super(description);

        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + time.replace("by", "by:") + ")";
    }

    @Override
    public String encode() {
        return "D | " +
                (isDone ? 1 : 0) +
                " | " + description +
                " | " + time;
    }
}
