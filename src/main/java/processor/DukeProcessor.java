package processor;

import commands.Command;
import commands.CommandType;
import commands.Commander;
import exceptions.DukeException;
import main.Duke;
import tasks.TaskList;

/**
 * Duke's processor class. Handles the processing of inputs and ensuring that Duke is running.
 */
public class DukeProcessor {

    private boolean isActive;
    private TaskList taskList;
    private Parser parser;

    public DukeProcessor() {
        init();
    }

    /**
     * Initiates startup, creating a TaskList and setting Duke to be active, after which Duke says hello.
     */
    private void init() {
        parser = new Parser(this);
        assert parser == null : "Parser initialisation error";
      
        taskList = new TaskList(this);
        assert taskList == null : "Task list initialisation error";

        isActive = true;
        assert isActive == false : "isActive initialisation error";
      
        sayHello();
    }

    /**
     * Processes the input entered by the user. Input is sent to be parsed by Parser class, then the Command returned
     * by Commander class is executed.
     *
     * @param input User input.
     */
    public String processInput(String input) {
        try {
            Command command = parser.parseInput(input);
            return command.execute(this, input);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Says hello.
     */
    public String sayHello() {
        Command sayHello = Commander.createCommand(CommandType.HI);
        try {
            return sayHello.execute(this, "");
        } catch (DukeException e) {
            return "";
        }
    }

    /**
     * Returns the TaskList of the processor.
     *
     * @return TaskList instantiated by DukeProcessor.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Disables Duke. He will be unable to process any input after this.
     */
    public void disable() {
        isActive = false;
    }

    /**
     * Checks if Duke is still active.
     *
     * @return Boolean that indicates true if Duke is active.
     */
    public boolean isActive() {
        return isActive;
    }
}
