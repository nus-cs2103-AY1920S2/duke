package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;

import java.util.ArrayList;

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
     * Executes Find behaviour of finding all tasks containing a keyword
     * and returns the result as a String
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     * @return a String representing the output.
     */
    public String executeWithBotResponse(TaskList taskList, Storage storage) {
        String output = "";
        ArrayList<Task> listToShow = taskList.getList();

        if (keyword.isBlank()) {
            output = "Sorry, I need a keyword.";
        } else {
            int count = 0;
            for (Task t: taskList.getList()) {
                if (t.getName().contains(keyword)) {
                    count++;
                    output += count + "." + t.toString() + "\n";
                }
            }
            output += "You have " + count + " matching task"
                    + (count != 1 ? "s" : "") + " for keyword: " + keyword;
        }

        return output;
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
