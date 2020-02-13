package duke.command;

import duke.exception.MissingDetailsException;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand implements Command {
    /**
     * todoCommand Method creates Todo Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws MissingDetailsException when unfilled secondary input is caught (empty commandSuffix)
     */
    public static String run(TaskList taskList, String commandSuffix) throws MissingDetailsException {
        try {
            taskList.add(new Todo(false, taskList.size(), commandSuffix));
            return ("Got it. I've added this task:\n\t[T][✗] "
                + commandSuffix + "\nNow you have " + taskList.size() + " task(s) in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingDetailsException();
        }
    }
}
