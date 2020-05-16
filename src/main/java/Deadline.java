
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadlineTime;

    public void setDeadlineTime(LocalDateTime deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public LocalDateTime getDeadlineTime() {
        return this.deadlineTime;
    }

    public Deadline(String name) {
        super(name);
        setDeadlineTime(LocalDateTime.MIN);
    }

    public Deadline(String name, String deadlineTime, Boolean isDone) {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        setDeadlineTime(LocalDateTime.parse(deadlineTime, formatter));
    }

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.deadlineTime = time;
    }

    public LocalDateTime getTime() {
        return this.deadlineTime;
    }

    @Override
    public String getDisplayName() {
        return getName() + "(by: " + getDeadlineTime() + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yy-M-d H:mm");
    }

    @Override
    public String getString() {
        DateTimeFormatter formatter = getDateTimeFormatter();
        return "{\n"
                + "Deadline" + "\n"
                + getName() + "\n"
                + getDeadlineTime().format(formatter) + "\n"
                + hasDone() + "\n"
                + "}";
    }

    /**
     * Create a deadline task.
     * @param reader Buffer to read from
     * @return A deadline task
     * @throws IOException if the command can't be parse
     */

    public static Deadline readBuffer(BufferedReader reader) throws IOException {
        String deadlineName = reader.readLine();
        String deadlineTime = reader.readLine();
        String isDone = reader.readLine();
        return new Deadline(deadlineName, deadlineTime, Boolean.valueOf(isDone));
    }
}
