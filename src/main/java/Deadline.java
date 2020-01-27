public class Deadline extends Task{
    protected String byDate;

    public Deadline(String command, String byDate) {
        super(command);
        this.byDate = byDate;
    }

    // e.g. D/Y/return book/June 5th
    @Override
    public String toStringTxt() {
        return "D/" + super.getIcon() + "/" + command + "/" + byDate + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  byDate + ")";
    }
}