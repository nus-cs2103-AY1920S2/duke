/**
 * Class to represent delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public DeleteCommand(String inputCommand, boolean isExit) {
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
        try {
            String[] inputParsed = this.getInputCommand().trim().split(" ");
            if (inputParsed.length <= 1) {
                throw new DukeException("Delete command cannot be empty!");
            }
            Task taskDeleted = list.deleteTask(Integer.parseInt(inputParsed[1]));
            ui.prettyPrinting(taskDeleted.toString() + " has been removed from the tasklist!");
            storage.writeToFile(list.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I believe you have an incorrect task number, try again!");
        }
    }
}
