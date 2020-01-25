import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Event implements Task {
    private final String name;
    private final boolean completed;
    private final String deadline;

    public Event(String name, String deadline) {
        this.name = name;
        this.completed = false;
        this.deadline = deadline;
    }

    public Event(Event event, boolean completed) {
        this.name = event.getName();
        this.completed = completed;
        this.deadline = event.getDeadline();
    }

    public Event(String name, String deadline, boolean completed) {
        this.name = name;
        this.completed = completed;
        this.deadline = deadline;
    }

    public Event makeCompleted() {
        return new Event(this, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public String writeFormat() {
        return "E|" + name + "|" + deadline + "|" + (completed ? "1" : "0");
    }

    public static Event readFormat(String s) {
        // assert here such that first token is D
        List<String> list = Collections.list(new StringTokenizer(s, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Event(list.get(1), list.get(2), Boolean.parseBoolean(list.get(3)));
    }

    @Override
    public String toString() {
        return "[E][" + (completed ? "✓" : "✗") + "] " + name + " (at: " + deadline + ")";
    }
}
