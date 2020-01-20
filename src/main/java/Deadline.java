public class Deadline extends Task {
    public final String date;

    public Deadline(String name, String date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, boolean completed, String date) {
        super(name, completed); 
        this.date = date;
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.name, true, this.date);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[D]" + doneCheck + this.name + " (by: " + date + ")";
        } else {
            return "[D]" + notDoneCheck + this.name + " (by: " + date + ")";
        }
    }
}