package duke.commands;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExitCommand implements Command {

    /**
     * Exits the program upon user input of "bye"
     */
    public static String execute() {
        // Exits system after one second, giving time to show exit message
        // @@author jadetayy-reused
        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> System.exit(0), 1, TimeUnit.SECONDS);
        return "Bye Patrick! Let's play soon!";
    }
}
