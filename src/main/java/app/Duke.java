package app;

import app.exceptions.BaseException;
import app.util.Pair;
import app.core.ConsoleInterface;
import app.core.commands.Command;
import app.core.commands.CommandManager;
import app.core.tasks.TaskManager;

/**
 * Main class for Duke. The class contains all the main components 
 * required for Duke to run properly.
 */
public final class Duke {
    /**
     * Welcome message that is printed upon starting Duke.
     */
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";

    /**
     * Goodbye message that is printed right before the program exits.
     */
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";
    public static final String UNEXPECTED_ERROR_MESSAGE = "Caught some other exception! Notify developer!";

    private CommandManager commandManager;
    private TaskManager taskManager;

    /**
     * Initializes a new instance of Duke.
     */
    public Duke() {
        this.commandManager = new CommandManager();
        this.taskManager = new TaskManager();
    }
    
    /**
     * Starts Duke.
     */
    public void start() {
        ConsoleInterface console = new ConsoleInterface();
        console.render(WELCOME_MESSAGE);
          
        while (!console.isClosed()) {
            try {
                String input = console.listen();
                Pair output = this.executeInput(input);
                String message = (String) output.getFirstValue();
                Boolean shutdown = (boolean) output.getSecondValue();

                if (!shutdown) {
                    console.render(message);
                } else {
                    console.close();
                }
            } catch (BaseException e) {
                console.renderError(e.getMessage());
            } catch (Exception e) {
                console.renderError(UNEXPECTED_ERROR_MESSAGE);
                console.renderError(e.getMessage());
            }
        }

        console.render(GOODBYE_MESSAGE);
    }

    /**
     * Processes the input based on Duke's configurations
     *     and returns the corresponding result.
     * @param input The input to be processed.
     * @return A Pair object containing the output values.
     * @throws BaseException If any exceptions occur during the execution
     *     of the input.
     */
    public Pair executeInput(String input) throws BaseException {
        Command command = this.commandManager.getCommand(input);
        Pair output = command.execute(this.taskManager);
        return output;
    }
}
