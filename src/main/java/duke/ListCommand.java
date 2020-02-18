package duke;

/**
 * List Command class that inherits from Command.
 */
public class ListCommand extends Command {

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in TaskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        try {
            String response;
            if (taskList.getTaskListSize() == 0) {
                throw new EmptyListException();
            } else {
                response = "Here are the task in your list: \n";
                for (int i = 0; i < taskList.getTaskListSize(); i++) {
                    response += (i + 1) + ". " + taskList.getTask(i).toString() + "\n";
                }
                return response;
            }
        } catch (DukeException ex) {
            return ex.toString();
        }
    }
}
