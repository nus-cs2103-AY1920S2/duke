import java.nio.file.Paths;

/**
 * Represents a chatbot, which takes in commands related to tasks and tracks a list of tasks.
 */
public abstract class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected static final String expectedStoragePath = Paths.get("data", "duke.txt").toString();
}
