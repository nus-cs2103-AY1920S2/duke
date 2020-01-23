public class Deadline extends Task {
    protected String deadline;

    Deadline(String unparsed) {
        String[] split = unparsed.split("/by");
        this.description = split[0].trim();
        this.deadline = split[1].trim();
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
