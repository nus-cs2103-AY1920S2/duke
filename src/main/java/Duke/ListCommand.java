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
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        try {
            if (taskList.getTaskListSize() == 0) {
                throw new EmptyListException();
            } else {
                System.out.println("Here are the task in your list:");
                for (int i = 0; i < taskList.getTaskListSize(); i++) {
                    System.out.println((i + 1) + ". " + taskList.getTask(i).toString());
                }
            }
        } catch (DukeException ex) {
            System.out.print(ex);
        }
    }
}
