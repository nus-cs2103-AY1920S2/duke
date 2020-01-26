package processor;

import commands.*;
import exceptions.DukeException;
import tasks.TaskList;

/**
 * Duke's processor class. Handles the processing of inputs and ensuring that Duke is running.
 */
public class DukeProcessor {

    private boolean isActive;
    private TaskList taskList;

    public DukeProcessor() {
        init();
    }

    /**
     * Initiates startup, creating a TaskList and setting Duke to be active, after which Duke says hello.
     */
    private void init() {
        taskList = new TaskList(this);
        isActive = true;
        sayHello();
    }

    /**
     * Processes the input entered by the user. Input is sent to be parsed by Parser class, then the Command returned
     * by Commander class is executed.
     * @param input User input.
     */
    public void processInput(String input) {
        Command command = Parser.parseInput(input);

        try {
            command.execute(this, input);
        } catch(DukeException e) {
            Ui.print(e.toString());
        }
    }

    /**
     * Says hello.
     */
    private void sayHello() {
        Command sayHello = Commander.createCommand(CommandType.HI);
        try {
            sayHello.execute(this, "");
        } catch(DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the TaskList of the processor.
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
     * @return Boolean that indicates true if Duke is active.
     */
    public boolean isActive() {
        return isActive;
    }
}
