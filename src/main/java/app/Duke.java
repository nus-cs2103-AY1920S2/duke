package app;

import app.exceptions.BaseException;
import app.core.UserInterface;
import app.core.commands.Command;
import app.core.commands.CommandManager;
import app.core.tasks.TaskManager;

public final class Duke {
    public static final String WELCOME_MESSAGE = "Wussup Dawggg! I'm Dukeee\nWhat you want me do?";
    public static final String GOODBYE_MESSAGE = "Bye!\nStay cool bruh! (((:";

    private UserInterface userInterface;
    private CommandManager commandManager;
    private TaskManager taskManager;

    public Duke() {
        this.userInterface = new UserInterface();
        this.commandManager = new CommandManager();
        this.taskManager = new TaskManager();
    }
    
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
