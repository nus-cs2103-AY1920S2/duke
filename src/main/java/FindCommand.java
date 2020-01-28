/**
 * Represents a find command that finds items from list. A <code>FindCommand</code> object
 * corresponds to a find command represented by the command and a description e.g.,
 * <code>"find", "read"</code>
 */
public class FindCommand extends Command {

    /**
     * A constructor for a find command object.
     * @param command command called
     * @param description description of command
     */
    public FindCommand(String command, String description) {
        super(command, description);
    }

    /**
     * Method for finding tasks containing keyword and listing them out.
     * @param tasks TaskList object
     * @param ui Ui object
     * @param storage Storage object
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String list = "";

        for(int i = 0; i < tasks.getRecord().size(); i++) {
            Task current = tasks.getRecord().get(i);

            if(current.getDescription().contains(description)) {
                list = list + (i + 1) + "." + current.toString() + "\n";
            } else {
                //do nothing
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Here are the matching tasks in your list: ");
        System.out.println(list);
        System.out.println("--------------------------------------------------\n");
    }
}
