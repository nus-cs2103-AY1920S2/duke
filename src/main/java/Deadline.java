public class Deadline extends Task {
    public String date;

    public Deadline(String description) {
        super(description);
        String[] arr = description.split(" /by ");
        this.description = arr[0];
        this.date = arr[1];
    }

    public String toPrint() {
        if (this.isDone) {
            return "D | " + 1 + " | " + this.description + " | " + this.date;
        } else {
            return "D | " + 0 + " | " + this.description + " | " + this.date;
        }
    }

    public String toString() {
        if (this.isDone) {
            return "[D][✓] " + this.description + " (by: " + this.date + ")";
        } else {
            return "[D][✗] " + this.description + " (by: " + this.date + ")";
        }
    }
}