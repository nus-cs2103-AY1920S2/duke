import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Deadline implements Task {
    private final String name;
    private final boolean completed;
    private final LocalDateTime deadline;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.completed = false;
        this.deadline = deadline;
    }

    public Deadline(String name, LocalDateTime deadline, boolean completed) {
        this.name = name;
        this.completed = completed;
        this.deadline = deadline;
    }

    public Deadline(Deadline d, boolean completed) {
        this.name = d.getName();
        this.completed = completed;
        this.deadline = d.getDeadline();
    }

    public Deadline makeCompleted() {
        return new Deadline(this, true);
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public String writeFormat() {
        return "D|" + name + "|" + deadline + "|" + (completed ? "1" : "0");
    }

    public static Deadline readFormat(String s) {
        // assert here such that first token is D
        List<String> list = Collections.list(new StringTokenizer(s, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Deadline(list.get(1), LocalDateTime.parse(list.get(2)), Boolean.parseBoolean(list.get(3)));
    }

    @Override
    public String toString() {
        return "[D][" + (completed ? "✓" : "✗") + "] " + name + " (by: "
                + deadline.format(formatter) + ")";
    }
}
