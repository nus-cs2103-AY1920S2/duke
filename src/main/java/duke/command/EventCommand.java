package duke.command;

import duke.exception.DukeException;
import duke.exception.MissingAtEventException;
import duke.exception.MissingDetailsException;
import duke.task.Event;
import duke.task.TaskList;

public class EventCommand implements Command {
    /**
     * eventCommand Method creates Event Tasks.
     *
     * @param taskList      is the list of Tasks are saved and manipulated
     * @param commandSuffix is the additional String that accompanies two-step commands
     * @throws DukeException when multiple exceptions are caught (e.g. unfilled secondary input)
     */
    public static String run(TaskList taskList, String commandSuffix) throws DukeException {
        try {
            String[] atEvent = commandSuffix.split(" /at ");

            try {
                taskList.add(new Event(false, taskList.size(), atEvent[0], atEvent[1]));
                return ("Got it. I've added this task:\n\t[E][✗] "
                    + atEvent[0] + " (at: " + atEvent[1] + ")"
                    + "\nNow you have " + taskList.size() + " task(s) in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingAtEventException();
            }
        } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
            throw new MissingDetailsException();
        }
    }
}
