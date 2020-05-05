import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    LocalDate date;

    Deadline(String name, boolean isDone, LocalDate date) {
        super(name, isDone);
        this.date = date;
    }

    public static Deadline decode(String text) {
        String[] parts = text.split(",");
        boolean decodedIsDone = parts[1].equals("1");
        LocalDate decodedDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Deadline(parts[2], decodedIsDone, decodedDate);
    }

    @Override
    public String encodeTask() {
        int encodedIsDone = this.getIsDone() ? 1 : 0;
        return "D," + encodedIsDone + "," + name + "," + this.getDateString();
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.getName(), true, this.date);
    }

    @Override
    public Task setName(String newName) {
        return new Deadline(newName, isDone, this.date);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[D]" + taskString + " (by: " + this.getDateString() + ")";
        return result;
    }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
