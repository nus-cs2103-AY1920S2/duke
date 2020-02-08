import java.time.format.DateTimeParseException;

/**
 * Represents a Command object that will be created when the user inputs the command to add a task.
 */
public class AddTaskCommand extends Command {
    private String input;

    public AddTaskCommand(String command, String input) {
        super(command);
        this.input = input;
    }

    /**
     * Executes the command to add a Task object to the task list,
     * by creating a task object from the input attribute of this object.
     * If the input string is not in the correct format of todo, event, deadline, an error will be thrown.
     * @param tasks TaskList object from the driver Duke object.
     * @param ui Ui object from the driver Duke object.
     * @param storage Storage object from the driver Duke object.
     * @throws DukeException Exception thrown by the method generateTask,
     *     if the given input string is of the wrong format.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            // create and add the task to the task list
            Task taskToAdd = this.generateTask(this.input);
            tasks.addTask(taskToAdd);
            // update and save the file
            storage.saveFile(tasks.getTaskList());
            // update the user of the new task list
            return ui.taskAddSuccess(taskToAdd, tasks.getTaskList().size());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns a Task object generated from the input String.
     * @param input a String of input from the user.
     * @return a Task object of the type corresponding to the input string that was given by the user.
     * @throws DukeException If input is not prepended correctly or if the body of the task is empty.
     */
    public Task generateTask(String input) throws DukeException {
        String[] splitInput = input.split(" ", 2);
        String taskType = splitInput[0];
        Task taskToReturn = null;
        try {
            String taskDetails = splitInput[1];

            switch (taskType) {
            case "todo":
                // Create a ToDo task and return it
                taskToReturn = new ToDo(taskDetails);
                break;
            case "deadline":
                taskToReturn = new Deadline(taskDetails);
                break;
            case "event":
                taskToReturn = new Event(taskDetails);
                break;
            default:
                break;
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            String message = "Details of a task cannot be empty!";
            throw new DukeException(message);
        } catch (DateTimeParseException de) {
            // if the date is entered in a wrong format, throw the error
            String message = "\t Wrong date format! Please input your date in the format yyyy-mm-dd";
            throw new DukeException(message);
        }

        return taskToReturn;
    }
}
