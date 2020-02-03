import java.time.format.DateTimeParseException;

public class AddTaskCommand extends Command {
    private boolean isExit;
    private String input;

    public AddTaskCommand(String command, String input) {
        super(command);
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
          try {
              // create and add the task to the task list
              Task taskToAdd = this.generateTask(this.input);
              tasks.addTask(taskToAdd);
              // update and save the file
              storage.saveFile(tasks.getTaskList());
              // update the user of the new task list
              ui.printTaskAddSuccess(taskToAdd, tasks.getTaskList().size());
          } catch (DukeException e) {
              throw new DukeException(e.getMessage());
          }
    }

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
