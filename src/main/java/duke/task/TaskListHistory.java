package duke.task;

import duke.Storage;
import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Stack;

/**
 * Keeps track of changes in TaskList.
 */
public class TaskListHistory {
    private static String saveFile = "duke.txt";
    private static Storage storage;
    private static TaskList initialTaskList;
    private static Stack<TaskList> stack = new Stack<>();

    static {
        // Load task list from save file
        try {
            storage = new Storage(saveFile);
            initialTaskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // Did not load tasks from save file
            initialTaskList = new TaskList(new ArrayList<>());
        }
        stack.add(initialTaskList);
    }

    /**
     * Resets the state of stack.
     */
    public static void reset() {
        stack = new Stack<>();
        stack.push(initialTaskList);
    }

    public static Stack<TaskList> getStack() {
        return stack;
    }

    public static TaskList getInitialTaskList() {
        return initialTaskList;
    }

    /**
     * Adds task list given to history if it is not null.
     *
     * @param taskList list of tasks
     * @return boolean indicating whether update was successful
     */
    public static boolean update(TaskList taskList) {
        if (taskList != null) {
            stack.push(taskList);
            return true;
        }
        return false;
    }

    /**
     * Returns the previous state of task list.
     *
     * @return TaskList of previous state, wrapped in an Optional
     */
    public static Optional<TaskList> getPreviousState() {
        int size = stack.size();
        if (size == 1) {
            // Initial state of program
            return Optional.of(initialTaskList);
        }
        // Remove latest item
        stack.pop();
        return Optional.of(stack.peek());
    }
}
