import duke.Duke;

/**
 * Executes a new Duke instance.
 */
public class Main {
    public static void main(String[] args) {
        new Duke("Duke", "data/tasks.txt").run();
    }
}