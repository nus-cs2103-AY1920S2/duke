package seedu.duke.command;

import seedu.duke.*;
import seedu.duke.enums.TaskTypes;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class AddCommand extends Command {
    private TaskTypes taskType;
    private String[] inputs;

    public AddCommand(TaskTypes taskType, String[] inputs) {
        this.taskType = taskType;
        this.inputs = inputs;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws EmptyDescriptionException,
            InvalidCommandException, InvalidTaskInputException, InvalidDateException, IOException {
        if (inputs.length == 1) {
            throw new EmptyDescriptionException();
        }
        String desc = inputs[1];
        String[] descs = desc.split(" /by |\\|");
        Task task = null;

        switch (taskType) {
        case TODO:
            task = new Todo(desc);
            break;
        case DEADLINE:
            if (descs.length == 1) { // invalid Deadline input format
                throw new InvalidTaskInputException();
            }

            String deadlineDesc = descs[0].trim();
            String deadlineDate = descs[1].trim();
            LocalDate formattedDeadlineDate = null;
            if (isValidDate(deadlineDate)) {
                formattedDeadlineDate = LocalDate.parse(deadlineDate);
            } else {
                throw new InvalidDateException();
            }
            task = new Deadline(deadlineDesc, formattedDeadlineDate);
            break;
        case EVENT:
            if (descs.length == 1) { // invalid Event input format
                throw new InvalidTaskInputException();
            }
            String eventDesc = descs[0].trim();
            String eventDate = descs[1].trim();
            LocalDate formattedEventDate = null;
            if (isValidDate(eventDate)) {
                formattedEventDate = LocalDate.parse(eventDate);
            } else {
                throw new InvalidDateException();
            }
            task = new Event(eventDesc, formattedEventDate);
            break;
        default:
            System.out.println("invalid");
            throw new InvalidCommandException();
        }

        taskList.addTasks(task);
        storage.addToStorage(task);
        ui.printAddToList();
        ui.print(task.toString());
        ui.printNumTask(taskList.getTasks());
    }

    @Override
    public boolean hasNextCommand() {
        return true;
    }

    /**
     * Checks if an input date is written in a valid date format.
     *
     * @param inDate The input date.
     * @return true if the input date is written in a valid date format.
     */
    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
