import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Todo implements Task {
    private final String name;
    private boolean isCompleted;

    public Todo(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public Todo(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public Todo(Todo t, boolean isCompleted) {
        this.name = t.getName();
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.isCompleted;
    }

    public void makeCompleted() {
        this.isCompleted = true;
    }

    public String writeFormat() {
        return "T|" + name + "|" + (isCompleted ? "1" : "0");
    }

    /**
     * Reads string formatted to store info regarding todotask.
     * @param input input string to be parsed
     * @return todotask object parsed from string information
     */
    public static Todo readFormat(String input) {
        // assert here such that first token is T
        List<String> list = Collections.list(new StringTokenizer(input, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Todo(list.get(1), Boolean.parseBoolean(list.get(2)));
    }

    @Override
    public String toString() {
        return "[T][" + (isCompleted ? "\u2713" : "\u2717") + "] " + name;
    }
}
