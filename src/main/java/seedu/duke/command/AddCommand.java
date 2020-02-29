package seedu.duke.command;

import seedu.duke.exception.EmptyDescriptionException;
import seedu.duke.exception.InvalidCommandException;
import seedu.duke.exception.InvalidDateException;
import seedu.duke.exception.InvalidInputFormatException;
import seedu.duke.exception.DukeException;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;
import seedu.duke.enums.TaskTypes;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a command object to add task to the list.
 */
public class AddCommand extends Command {
    private TaskTypes taskType;
    private String[] inputs;

    /**
     * Represents an AddCommand object.
     *
     * @param taskType The type of the task.
     * @param inputs The user input.
     */
    public AddCommand(TaskTypes taskType, String[] inputs) {
        this.taskType = taskType;
        this.inputs = inputs;
    }

    /**
     * Adds a task into the list.
     *
     * @param taskList The TaskList object.
     * @param ui The User Interface object.
     * @param storage The hard disk object.
     * @throws IOException If an input or output exception occurred.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        try {
            if (inputs.length == 1) {
                throw new EmptyDescriptionException();
            }
            String desc = inputs[1];
            Task task = null;

            switch (taskType) {
            case TODO:
                task = new Todo(desc);
                break;
            case DEADLINE:
                String[] deadlineDescs = desc.split(" /by |\\|");
                if (deadlineDescs.length == 1) { // invalid Deadline input format
                    throw new InvalidInputFormatException();
                }

                String deadlineDesc = deadlineDescs[0].trim();
                String deadlineDate = deadlineDescs[1].trim();
                if (deadlineDate.length() != 10) {
                    throw new InvalidDateException();
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date formattedDeadlineDate = null;
                try {
                    formattedDeadlineDate = dateFormat.parse(deadlineDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }


                task = new Deadline(deadlineDesc, formattedDeadlineDate);
                break;
            case EVENT:
                String[] eventDescs = desc.split(" /at |\\|");
                if (eventDescs.length == 1) { // invalid Event input format
                    throw new InvalidInputFormatException();
                }
                String eventDesc = eventDescs[0].trim();
                String eventDate = eventDescs[1].trim();
                if (eventDate.length() != 10) {
                    throw new InvalidDateException();
                }
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date formattedEventDate = null;
                try {
                    formattedEventDate = dateFormat.parse(eventDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                task = new Event(eventDesc, formattedEventDate);
                break;
            default:
                throw new InvalidCommandException();
            }

            taskList.addTask(task);
            storage.addToStorage(task);
            storage.sortStorage();
            ui.printAddToList();
            ui.print(task.toString());
            ui.printNumTask(taskList.getTasks());
        } catch (DukeException e) {
            ui.print(e.toString());
        }

    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }

    //@@author johannagwan-reused
    //Reused from http://www.java2s.com/Tutorial/Java/0120__Development/CheckifaStringisavaliddate.htmv
    //with minor modifications.
    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate The input date.
     * @return true if the input date is written in a valid date format.
     */
    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    //@@author
}
