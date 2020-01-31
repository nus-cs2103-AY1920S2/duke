import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * FindCommand inherits from Command and is used when user input starts with "find".
 */
class FindCommand extends Command {
    protected String command;

    /**
     * Constructor for FindCommand which takes in the input keyed in by user.
     *
     * @param command
     */
    FindCommand(String command) {
        this.command = command;
    }

    /**
     * Used to find the tasks that match the user's input.
     *
     * @param tasks This is the saved TaskList in duke.txt.
     * @param ui This is the created Ui in Duke.
     * @param storage This is responsible for loading and saving the updated TaskList.
     */
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String commandWithoutFind = this.command.substring(5).trim();
        List<Task> matchedList = new ArrayList<>();
        for (Task task : tasks.getTaskList()) {
            String[] taskStringArray = task.toString().split(" ");
            List<String> listToBeChecked = new ArrayList<String>(Arrays.asList(taskStringArray));
            if (listToBeChecked.contains(commandWithoutFind)) {
                matchedList.add(task);
            }
        }
        System.out.println("Here are the matching tasks in the list:" + "\n");
        for (int i = 0; i < matchedList.size(); i++) {
            System.out.println(i + 1 + " . " + matchedList.get(i));
        }
    }
}
