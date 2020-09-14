package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.CommandType;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.tasks.Deadline;
import dukeproj.tasks.Event;
import dukeproj.tasks.Task;
import dukeproj.tasks.Todo;

/**
 * Represents a command to add a task into Duke's task list.
 */
public class AddCommand extends Command {
    /** The description of the task to be added. */
    private String description;
    /** The type of the task to be added. */
    private CommandType type;

    /**
     * Executes an add command to add a certain task into Duke's task list.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList The list of tasks to add to.
     * @param storage The object to assist in writing the task list into the storage file.
     * @param schedule Duke's schedule to be modified if added task is date sensitive.
     * @return Duke's response in the form of a String.
     * @throws BadDescriptionException If the description is missing a '/', for Event and Deadline cases only.
     * @throws DukeDescriptionException If the description is empty.
     * @throws BadDateException If the date provided does not follow the required date format.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws BadDescriptionException, DukeDescriptionException, BadDateException {
        if (description.isEmpty()) {
            throw new DukeDescriptionException("Empty Description");
        }
        Task task = addTask();
        String output = ui.say(SayType.ADD) + "\n" + task;
        if (!type.equals(CommandType.TODO)) {
            //Add task into schedule if it is event or deadline
            schedule.addDate(task);
        }
        taskList.addTask(task);
        storage.writeListIntoFile(taskList.getList());
        return output;
    }

    private Task addTask() throws BadDescriptionException, BadDateException {
        switch (type) {
        case TODO:
            return new Todo(description);
        case EVENT:
            int eventDate = description.indexOf("/");
            if (eventDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            return new Event(description.substring(0, eventDate),
                    description.substring(eventDate + 4));
        case DEADLINE:
            int deadlineDate = description.indexOf("/");
            if (deadlineDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            return new Deadline(description.substring(0, deadlineDate),
                    description.substring(deadlineDate + 4));
        default:
            System.err.println("Bad command type parsed into AddCommand, task defaults to Todo");
            return new Todo(description); //default is todo task.
        }
    }

    /**
     * Constructs an add command object.
     *
     * @param description Description of the task to be added.
     * @param type Type of the task to be added.
     */
    public AddCommand(String description, CommandType type) {
        this.description = description;
        this.type = type;
    }
}
