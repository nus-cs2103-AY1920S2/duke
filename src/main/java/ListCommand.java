/**
 * ExitCommand inherits from Command and is called to print
 * the current TaskList when user inputs "list".
 */
class ListCommand extends Command {

    /**
     * Used to print the current TaskList to the user.
     *
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getTaskList().size() == 0) {
            ui.showLine();
            System.out.println("Your current list of tasks is empty! Try adding some tasks.");
        } else {
            ui.showLine();
            System.out.println("\n" + "Here are your tasks!");
            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                System.out.println(i + 1 + ". " + tasks.getTaskList().get(i));
            }
        }
    }

    /**
     * Returns true if the command is an ExitCommand and false otherwise.
     * @return false
     */
    @Override
    boolean isExit() {
        return false;
    }
}
