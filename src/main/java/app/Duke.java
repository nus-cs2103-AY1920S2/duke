package app;

import app.exceptions.BaseException;
import app.util.Pair;
import app.core.UserInterface;
import app.core.commands.Command;
import app.core.commands.CommandManager;
import app.core.tasks.TaskManager;

/**
 * Main class for Duke. The class contains all the main components 
 * required for Duke to run properly.
 */
public final class Duke {
    /**
     * Welcome message that is printed upon starting Duke
     */
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";

    /**
     * Goodbye message that is printed right before the program exits
     */
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";

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
     * Starts Duke
     */
    public void start() {
        UserInterface userInterface = new UserInterface();
        userInterface.render(WELCOME_MESSAGE);
          
        while (!userInterface.isClosed()) {
            try {
                String input = userInterface.listen();
                Pair output = this.executeInput(input);
                String message = (String) output.getFirstValue();
                Boolean shutdown = (boolean) output.getSecondValue();

                if (!shutdown) {
                    userInterface.render(message);
                } else {
                    userInterface.close();
                }
            } catch (BaseException e) {
                userInterface.renderError(e.getMessage());
            } catch (Exception e) {
                userInterface.renderError("Caught some other exception! Notify developer!");
                userInterface.renderError(e.getMessage());
            }
        }

        userInterface.render(GOODBYE_MESSAGE);
    }

    public Pair executeInput(String input) throws BaseException {
        Command command = this.commandManager.getCommand(input);
        Pair output = command.execute(this.taskManager);
        return output;
    }
}
