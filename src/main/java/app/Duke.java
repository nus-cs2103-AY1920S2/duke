package app;

import app.exceptions.BaseException;
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

    private UserInterface userInterface;
    private CommandManager commandManager;
    private TaskManager taskManager;

    /**
     * Initializes a new instance of Duke.
     */
    public Duke() {
        this.userInterface = new UserInterface();
        this.commandManager = new CommandManager();
        this.taskManager = new TaskManager();
    }
    
    /**
     * Starts Duke
     */
    public void start() {
        this.userInterface.render(WELCOME_MESSAGE);
          
        while (!this.userInterface.isClosed()) {
            try {
                String input = this.userInterface.listen();
                Command command = this.commandManager.getCommand(input);
                command.execute(this.taskManager, this.userInterface);
            } catch (BaseException e) {
                this.userInterface.renderError(e.getMessage());
            } catch (Exception e) {
                this.userInterface.renderError("Caught some other exception! Notify developer!");
                this.userInterface.renderError(e.getMessage());
            }
        }

        this.userInterface.render(GOODBYE_MESSAGE);
    }
}
