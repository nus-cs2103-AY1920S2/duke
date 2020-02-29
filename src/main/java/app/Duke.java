package app;

import app.exceptions.BaseException;
import app.util.Pair;
import app.core.ConsoleInterface;
import app.core.commands.Command;
import app.core.commands.CommandManager;
import app.core.tasks.TaskManager;
import app.core.Messages;

/**
 * Main class for Duke. The class contains all the main components 
 * required for Duke to run properly.
 */
public final class Duke {
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
        console.render(Messages.WELCOME_MESSAGE);

        String input = "";
        String output = "";
        boolean shutdown = false;
          
        while (!console.isClosed()) {
            input = console.listen();
            
            try {
                Pair result = this.executeInput(input);
                output = (String) result.getFirstValue();
                shutdown = (boolean) result.getSecondValue();
                assert output != null : "Output message should not be null";

                console.render(output);
            } catch (BaseException e) {
                console.renderError(e.getMessage());
            } catch (Exception e) {
                console.renderError(Messages.UNEXPECTED_ERROR_MESSAGE);
                console.renderError(e.getMessage());
            } finally {
                if (shutdown) {
                    console.close();
                }
            }
        }
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
        
        assert output != null : "Command execution should not be null";
        return output;
    }
}
