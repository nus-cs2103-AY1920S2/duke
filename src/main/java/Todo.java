import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Todo implements Task {
    private final String name;
    private final boolean completed;

    public Todo(String name) {
        this.name = name;
        this.completed = false;
    }

    public Todo(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public Todo(Todo t, boolean completed) {
        this.name = t.getName();
        this.completed = completed;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.completed;
    }

    public Todo makeCompleted() {
        return new Todo(this, true);
    }

    public String writeFormat() {
        return "T|" + name + "|" + (completed ? "1" : "0");
    }

    public static Todo readFormat(String s) {
        // assert here such that first token is T
        List<String> list = Collections.list(new StringTokenizer(s, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Todo(list.get(1), Boolean.parseBoolean(list.get(2)));
    }

    @Override
    public String toString() {
        return "[T][" + (completed ? "✓" : "✗") + "] " + name;
    }
}
