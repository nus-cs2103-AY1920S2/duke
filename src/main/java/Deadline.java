public class Deadline extends Task {
    private String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    private Deadline(String name, String date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    public String toString() {
        return "[D]" + super.toString() + "(by: " + date + ")";
    }

    public Deadline setDone() {
        return new Deadline(this.name, this.date, true);
    }
}
