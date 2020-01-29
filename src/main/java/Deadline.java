

public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {

        super(description);
        int newDeadlineFormat = deadline.indexOf(" ");
        this.deadline = deadline.substring(0, newDeadlineFormat) + ":" + deadline.substring(newDeadlineFormat);

    }

    @Override
    public String saveToHardDiskFormat() {

        return String.format("D | %d | %s | %s", this.completedCode, this.getDescription(), this.deadline.replace("by: ", ""));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.deadline + ")";
    }
}
