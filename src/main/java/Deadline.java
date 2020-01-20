public class Deadline extends Task {
    public final String date;

    public Deadline(String name, boolean completed, String date) {
        super(name, completed); 
        this.date = date;
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[D]" + doneCheck + this.name;
        } else {
            return "[D]" + notDoneCheck + this.name;
        }
    }
}