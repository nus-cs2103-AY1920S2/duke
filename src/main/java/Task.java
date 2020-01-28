import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public static Task getTaskFromMemory(String line) throws DukeException {
        String[] splitted = line.split(" \\| ");

        switch(splitted[0]) {
            case "T":
                return new ToDo(splitted[1]);
            case "D":
                return new Deadline(splitted[1], splitted[2]);
            case "E":
                return new Event(splitted[1], splitted[2]);
            default:
                throw new DukeException("Invalid file. File may have been corrupted.");
        }
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }

    public String toFileFormat() {
        return String.format("%s | %d | %s", "T", this.isDone ? 1 : 0, this.description);
    }
    
    public static LocalDate convertToLocalDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(input, formatter);
    }
}
