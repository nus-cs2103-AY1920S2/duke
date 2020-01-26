package duke.command;

import duke.interaction.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.util.Storage;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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

    public boolean isExit() { return false; }
}
