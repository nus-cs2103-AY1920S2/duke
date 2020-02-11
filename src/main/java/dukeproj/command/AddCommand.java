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
     * @param schedule Duke's calender to be modified if added task is date sensitive.
     * @return Duke's response in the form of a String.
     * @throws BadDescriptionException If the description is missing a '/', for Event and Deadline cases only.
     * @throws DukeDescriptionException If the description is empty.
     * @throws BadDateException If the date provided does not follow the required date format.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws BadDescriptionException, DukeDescriptionException, BadDateException {
        String output = "";
        switch (type) {
        case TODO:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            Task taskToDo = new Todo(description);
            taskList.addTask(taskToDo);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskToDo;
            break;
        case EVENT:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int eventDate = description.indexOf("/");
            if (eventDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskEvent = new Event(description.substring(0, eventDate),
                    description.substring(eventDate + 4));
            taskList.addTask(taskEvent);
            schedule.addDate(taskEvent);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskEvent;
            break;
        case DEADLINE:
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }
            int deadlineDate = description.indexOf("/");
            if (deadlineDate == -1) {
                throw new BadDescriptionException("Missing '/' in Description");
            }
            Task taskDLine = new Deadline(description.substring(0, deadlineDate),
                    description.substring(deadlineDate + 4));
            taskList.addTask(taskDLine);
            schedule.addDate(taskDLine);
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.ADD) + "\n" + taskDLine;
            break;
        default:
            break;
        }
        return output;
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
