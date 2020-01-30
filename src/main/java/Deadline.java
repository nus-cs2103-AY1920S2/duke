import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Deadline implements Task {
    private final String name;
    private final boolean isCompleted;
    private final LocalDateTime deadline;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    /**
     * Creates a Deadline object with a name as its description and a LocalDateTime. Assumes
     * that Deadline is incomplete by definition.
     * @param name Name of Deadline
     * @param deadline LocalDateTime object of Deadline
     */
    public Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.isCompleted = false;
        this.deadline = deadline;
    }

    /**
     * Constructor for Deadline with all three inputs - name, deadline and isCompleted.
     * @param name Name of Deadline
     * @param deadline LocalDateTime object of Deadline
     * @param isCompleted Boolean value if Deadline is completed or not.
     */
    public Deadline(String name, LocalDateTime deadline, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        this.deadline = deadline;
    }

    /**
     * Takes in another Deadline object and modifies its isCompleted value.
     * @param deadline reference deadline object whose name and deadline is cloned over
     * @param isCompleted Boolean value if Deadline is completed or not.
     */
    public Deadline(Deadline deadline, boolean isCompleted) {
        this.name = deadline.getName();
        this.isCompleted = isCompleted;
        this.deadline = deadline.getDeadline();
    }

    public Deadline makeCompleted() {
        return new Deadline(this, true);
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
        return "D|" + name + "|" + deadline + "|" + (isCompleted ? "1" : "0");
    }

    /**
     * Reads string formatted to store info regarding a Deadline object.
     * @param input input string to be parsed
     * @return Deadline object parsed from string information
     */
    public static Deadline readFormat(String input) {
        // assert here such that first token is D
        List<String> list = Collections.list(new StringTokenizer(input, "|")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
        return new Deadline(list.get(1), LocalDateTime.parse(list.get(2)), Boolean.parseBoolean(list.get(3)));
    }

    @Override
    public String toString() {
        return "[D][" + (isCompleted ? "✓" : "✗") + "] " + name + " (by: "
                + deadline.format(formatter) + ")";
    }
}
