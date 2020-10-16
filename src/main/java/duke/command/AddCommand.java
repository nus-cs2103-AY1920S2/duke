package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.io.Ui;
import duke.task.*;

/**
 * handles the add command
 */
public class AddCommand extends Command {
    private final String command;
    private final String description;

    /**
     * Constructs a AddCommand and sets command type and description.
     *
     * @param command the type of command subclass that this.execute() produces
     * @param description  additional data the command may need to execute.
     */
    public AddCommand(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * Adds one of the subclasses of Command (based to this class's private command field)
     * to the taskList.
     *
     * @param tasksList the taskList to add the new Task to
     * @param ui used to print successful addition of a task to taskList
     * @param storage not used
     * */
    @Override
    public void execute(TasksList tasksList, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        switch (command) {
        case "todo":
            newTask = tasksList.addTodo(description, false);
            break;
        case "deadline":
            newTask = tasksList.addDeadline(description, false);
            break;
        case "event":
            newTask = tasksList.addEvent(description, false);
            break;
        }

        ui.printSuccessfulAddEntry(tasksList, newTask);
        storage.saveTasksList(tasksList);
    }

}
