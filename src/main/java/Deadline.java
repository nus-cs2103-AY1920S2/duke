public class Deadline extends Task {

    protected String by;

    public Deadline (String description, String by) {
        super (description);
        this.by = by;
    }

    @Override
    public String saveFile() {
        if (this.status.equals("Done")) {
            return  "D|1||" + this.description + "|||" + this.by;
        } else {
            return  "D|0||" + this.description + "|||" + this.by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
