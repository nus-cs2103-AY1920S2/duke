package duke.state;


import duke.command.Command;
import duke.command.DoneCommand;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Stack;

/**
 * A controller class that manages states of the program.
 */
public class StateController {
    Stack<State> stateStack = new Stack<>();
    State current;
    State next;

    /**
     * Constructs a StateController class with the task list consistent with what is read from data.csv.
     *
     * @param taskList an ArrayList of Task objects
     */
    public StateController(ArrayList<Task> taskList) {
        // Makes initial state which we set current to
        this.stateStack.push(new State(null, taskList));
        current = stateStack.peek();
    }

    /**
     * Adds a new state to the current state storage.
     *
     * @param command  a Command object
     * @param taskList an ArrayList of Task objects.
     */
    public void commit(Command command, ArrayList<Task> taskList) {
        this.stateStack.push(new State(command, taskList));
        current = stateStack.peek();
        next = null;
    }

    /**
     * Restores the state of the program to the previous state by popping the stateStack stack.
     *
     * @throws Exception when there are no state other than the initial one.
     */
    public void undo() throws Exception {
        if (stateStack.peek().getCommand() != null) {
            if (current.getCommand() instanceof DoneCommand) {
                int index = ((DoneCommand) current.getCommand()).getIndex();
                current.getTaskList().get(index).setUndone();
            }
            stateStack.pop();
            current = stateStack.peek();
        } else {
            throw new Exception(" Sorry I cannot find a past state.");
        }
    }

    /**
     * Returns the current state of the program.
     *
     * @return a State object.
     */
    public State getCurrent() {
        return this.current;
    }

}
