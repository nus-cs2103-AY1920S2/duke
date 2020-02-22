package duke.state;

import duke.command.Command;
import duke.task.Task;

import java.util.ArrayList;

/**
 * An object representing a state of the Duke program. A state consists of the task list as well as the command that
 * results in it.
 */
public class State {
    Command command;
    ArrayList<Task> taskList;

    /**
     * Constructs a new State object
     *
     * @param command  a Command that results in the state. When the program, and its StateController are initialized,
     *                 a State object with this field set to null will be used to represent the initial state.
     * @param taskList an ArrayList of Task objects.
     */
    State(Command command, ArrayList<Task> taskList) {
        this.command = command;
        this.taskList = taskList;
    }

    /**
     * Returns the command of this state.
     *
     * @return a State object.
     */
    public Command getCommand() {
        return this.command;
    }

    /**
     * Returns the task list of this state.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
