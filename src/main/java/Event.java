import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task
 */
public class Event extends Task {
    LocalDate date;

    Event(String name, boolean isDone, LocalDate date) {
        super(name, isDone);
        this.date = date;
    }

    public static Event decode(String text) {
        String[] parts = text.split(",");
        boolean decodedIsDone = parts[1].equals("1");
        LocalDate decodedDate = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("MMM d yyyy"));
        return new Event(parts[2], decodedIsDone, decodedDate);
    }

    @Override
    public String encodeTask() {
        int encodedIsDone = this.getIsDone() ? 1 : 0;
        return "E," + encodedIsDone + "," + this.getName() + "," + this.getDateString();
    }

    @Override
    public Event complete() {
        return new Event(this.getName(), true, this.date);
    }

    @Override
    public Task setName(String newName) {
        return new Event(newName, isDone, this.date);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[E]" + taskString + " (at: " + this.getDateString() + ")";
        return result;
    }

    public String getDateString() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}