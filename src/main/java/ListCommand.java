/**
 * Class to represent listing command.
 */
public class ListCommand extends Command {

    /**
     * Constructor.
     * @param inputCommand command inputted by user.
     * @param isExit whether to exit the chat bot or not.
     */
    public ListCommand(String inputCommand, boolean isExit) {
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
        int counter = 1;
        String listings = "";
        for (Task task : list.getTaskList()) {
            listings += counter + "." + task.toString();
            if (counter != list.getSize()) {
                listings += "\n\t";
            }
            counter++;
        }
        ui.prettyPrinting(listings);
    }
}
