import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Deadline implements Task {
    private final String name;
    private final boolean completed;
    private final String deadline;

    public Deadline(String name, String deadline) {
        this.name = name;
        this.completed = false;
        this.deadline = deadline;
    }

    public Deadline(String name, String deadline, boolean completed) {
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

    public String getDeadline() {
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
        return new Deadline(list.get(1), list.get(2), Boolean.parseBoolean(list.get(3)));
    }

    @Override
    public String toString() {
        return "[D][" + (completed ? "✓" : "✗") + "] " + name + " (by: " + deadline + ")";
    }
}
