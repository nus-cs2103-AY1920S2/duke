
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime time;

    public Event(String name, String time, boolean isDone) {
        super(name, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        this.time = LocalDateTime.parse(time, formatter);
    }

    public Event(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String getDisplayName() {
        return getName() + "(at: " + time + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    private DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yy-M-d H:mm");
    }

    @Override
    public String getString() {
        DateTimeFormatter formatter = getDateTimeFormatter();
        return "{\n"
                + "Event\n"
                + getName() + "\n"
                + getTime().format(formatter) + "\n"
                + hasDone() + "\n"
                + "}";
    }

    /**
     * Create an event task from buffer
     * @param reader File buffer to read from
     * @return an event
     * @throws IOException
     */

    public static Event readBuffer(BufferedReader reader) throws IOException {
        String eventName = reader.readLine();
        String eventTime = reader.readLine();
        String isDone = reader.readLine();
        return new Event(eventName, eventTime, Boolean.valueOf(isDone));
    }
}
