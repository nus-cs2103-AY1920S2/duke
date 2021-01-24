import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Todo extends Task {
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

    public String getName() {
        return this.name;
    }

    public boolean getCompletion() {
        return this.isCompleted;
    }

    public void makeCompleted() {
        this.isCompleted = true;
    }

    /**
     * Outputs TodoTask Object as a string for text files.
     * @return String to be appended to text files
     */
    public String writeFormat() {
        StringBuilder output = new StringBuilder("T|" + name + "|" + (isCompleted ? "1" : "0"));
        if (this.getTags().size() > 0) {
            output.append("|");
            this.getTags().forEach(x -> output.append("#").append(x));
        }
        return output.toString();
    }

    /**
     * Reads string formatted to store info regarding todotask.
     * @param input input string to be parsed
     * @return todotask object parsed from string information
     */
    public static Todo readFormat(String input) {
        assert input.charAt(0) == 'T';
        List<String> list = Collections.list(new StringTokenizer(input, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        Todo todo = new Todo(list.get(1), Boolean.parseBoolean(list.get(2)));
        if (list.size() == 4) {
            List<String> tags = Collections.list(new StringTokenizer(list.get(3), "#")).stream()
                    .map(token -> (String) token)
                    .collect(Collectors.toList());
            tags.remove(0);
            tags.forEach(todo::addTag);
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[T][" + (isCompleted ? "\u2713" : "\u2717") // output is tick and cross
                + "] " + name + " " + super.toString();
    }
}
