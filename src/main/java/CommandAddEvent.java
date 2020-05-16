import java.time.LocalDateTime;
import java.util.Date;

public class CommandAddEvent extends Command {
    private String eventName;
    private LocalDateTime eventTime;

    public CommandAddEvent(String eventName, LocalDateTime eventTime) {
        this.eventName = eventName;
        this.eventTime = eventTime;
    }

    @Override
    public String run(TaskList tasks, Storage storage, UI ui) {
        Event newEvent = new Event(eventName, eventTime);
        String message = "Add new event: " + newEvent.getName() + " at " + newEvent.getTime();
        tasks.add(newEvent);

        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
