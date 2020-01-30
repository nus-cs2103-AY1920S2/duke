import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private LocalDate date;

    public Event(String task) {
        // Pass the description of the task as the argument to the parent constructor
        super(task.split("/at")[0]);
        String[] arr = task.split("/at");
        // Get the date from the arr and set the date
        this.date = LocalDate.parse(arr[1].trim());
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String date = this.getDate();

        return "[E]" + "[" + status + "] " + description + " - " + date;
    }
}
