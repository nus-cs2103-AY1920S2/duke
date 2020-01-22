public class Event extends Task {
    public String date;

    public Event(String description) {
        super(description);
        String[] arr = description.split(" /at ");
        this.description = arr[0];
        this.date = arr[1];
    }

    public String toString() {
        if (this.isDone) {
            return "[E][✓] " + this.description + " (at: " + this.date + ")";
        } else {
            return "[E][✗] " + this.description + " (at: " + this.date + ")";
        }
    }
}