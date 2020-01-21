public class Deadline extends Task {
    private String time;

    public Deadline(String deadline) {
        super(deadline.split("/")[0]);
        this.time = deadline.split("/")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }
}
