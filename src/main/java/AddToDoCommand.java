/**
 * Class to represent add To-Do command.
 */
public class AddToDoCommand  extends Command {

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public AddToDoCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    /**
     * Execute the given task.
     * @param ui the Ui class in charge of aesthetics.
     * @param list tasklist that stores all the task.
     * @param storage storage that writes to file.
     * @throws DukeException exception.
     */
    @Override
    public void execute(UI ui, TaskList list, Storage storage) throws DukeException {
        String[] inputParsed = (this.getInputCommand()).trim().split(" ");
        if (inputParsed.length == 1) {
            throw new DukeException("ToDo description cannot be empty!");
        }
        StringBuilder taskName = new StringBuilder(); //store task name
        for (int i = 1; i < inputParsed.length; i++) {
            taskName.append(inputParsed[i]);
            if (i != inputParsed.length - 1) {
                taskName.append(" ");
            }
        }
        ToDo newToDo = new ToDo(taskName.toString());
        list.addTask(newToDo); //add to task list
        ui.prettyPrinting(taskName.toString() + " added!");
        storage.writeToFile(list.getTaskList()); //save to file
    }
}
