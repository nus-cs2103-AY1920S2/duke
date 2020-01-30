import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Event implements Task {
    private final String name;
    private final boolean isCompleted;
    private final LocalDateTime deadline;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Creates an Event object with a name as its description and a LocalDateTime. Assumes
     * that Event is incomplete by definition.
     * @param name Name of Event
     * @param deadline LocalDateTime object of Event
     */
    public Event(String name, LocalDateTime deadline) {
        this.name = name;
        this.isCompleted = false;
        this.deadline = deadline;
    }

    /**
     * Takes in another Event object and modifies its isCompleted value.
     * @param event reference Event object whose name and deadline is cloned over
     * @param isCompleted Boolean value if Event is completed or not.
     */
    public Event(Event event, boolean isCompleted) {
        this.name = event.getName();
        this.isCompleted = isCompleted;
        this.deadline = event.getDeadline();
    }

    /**
     * Constructor for Event with all three inputs - name, deadline and isCompleted.
     * @param name Name of Event
     * @param deadline LocalDateTime object of Event
     * @param isCompleted Boolean value if Event is completed or not.
     */
    public Event(String name, LocalDateTime deadline, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
    }

    public Event makeCompleted() {
        return new Event(this, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.isCompleted;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String writeFormat() {
        return "E|" + name + "|" + deadline + "|" + (isCompleted ? "1" : "0");
    }

    /**
     * Reads string formatted to store info regarding an Event object.
     * @param input input string to be parsed
     * @return Event object parsed from string information
     */
    public static Event readFormat(String input) {
        // assert here such that first token is D
        List<String> list = Collections.list(new StringTokenizer(input, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Event(list.get(1), LocalDateTime.parse(list.get(2)), Boolean.parseBoolean(list.get(3)));
    }

    @Override
    public String toString() {
        return "[E][" + (isCompleted ? "✓" : "✗") + "] " + name + " (at: "
                + deadline.format(formatter) + ")";
    }
}
