
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime time;
    public Event(String name, String time) {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-M-d H:mm");
        this.time = LocalDateTime.parse(time, formatter);
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
        return "{\n" +
                "Event\n" +
                getName() + "\n" +
                getTime().format(formatter) + "\n" +
                "}";
    }
    public static Event readBuffer(BufferedReader reader) throws IOException {
        String eventName = reader.readLine();
        String eventTime = reader.readLine();
        return new Event(eventName, eventTime);
    }
}
