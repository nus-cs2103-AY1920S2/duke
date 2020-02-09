package duke.task;

import duke.DukeException;
import duke.Storage;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Optional;
import java.util.Stack;

/**
 * Keeps track of changes in TaskList.
 */
public class TaskListHistory {
    private static String saveFile = "duke.txt";
    private static Storage storage = new Storage(saveFile);
    private static TaskList initialTaskList;
    private static Stack<TaskList> stack = new Stack<>();

    static {
        // Load task list from save file
        try {
            initialTaskList = new TaskList(storage.load());
        } catch (DukeException e) {
            // Did not load tasks from save file
            initialTaskList = new TaskList(new ArrayList<>());
        }
        stack.add(initialTaskList);
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
        try {
            // Remove latest item
            stack.pop();
            return Optional.of(stack.peek());
        } catch (EmptyStackException e) {
            return Optional.empty();
        }
    }
}
