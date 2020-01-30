package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;

/**
 * Represents the Command for the "find" input by the user.
 * It shows all task items that contain the subsequent keyword from the user.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * FindCommand constructor.
     * @param keyword of the word user wants to match.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes Find behaviour of finding all tasks containing a keyword.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        Ui.showLine();
        if (keyword.isBlank()) {
            Ui.printWithIndent("Sorry, I need a keyword.");
        } else {
            int count = 0;
            for (Task t: taskList.getList()) {
                if (t.getName().contains(keyword)) {
                    count++;
                    Ui.printWithIndent(count + "." + t.toString());
                }
            }
            Ui.printWithIndent("You have " + count + " matching task" + (count != 1 ? "s" : "")
                    + " for keyword: " + keyword);
        }
        Ui.showLine();
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
