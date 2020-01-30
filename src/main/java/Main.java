import duchess.Duchess;

/**
 * The {@code Main} class is the entry point to the Duchess program.
 */
public class Main {
    /**
     * Starts up the Duchess program.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Duchess duchess = new Duchess("data/tasks.json");
        duchess.run();
    }
}
