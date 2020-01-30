public class Deadline extends Task {
    protected String deadline;

    public Deadline(String command, String deadline) {
        super(command);
        this.deadline = deadline;
    }

    public int getDoneInt() {
        return getDone() ? 1 : 0;
    }

    @Override
    public String updateFile() {
        return "D - " + getDoneInt() + " - " + getCommand() + "- " + deadline;
    }

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + "(by: " + deadline + ")";
    }
}
