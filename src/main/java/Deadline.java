import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String task) throws DateTimeParseException {
        // Split the input task string by the delimiter /by
        // first element in arr will be the description of the task, second elem will be the deadline, both are String
        // Pass the description as the argument to the constructor
        super(task.split("/by")[0]);
        String[] arr = task.split("/by");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            this.deadline = LocalDate.parse(arr[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }

    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String deadline = this.getDeadline();

        return "[D]" + "[" + status + "] " + description + "by: " + deadline;

    }
}
