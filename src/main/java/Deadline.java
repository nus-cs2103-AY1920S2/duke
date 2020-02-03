public class Deadline extends Task {
    public Deadline(String description, String endTime) {
        super(description, endTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}