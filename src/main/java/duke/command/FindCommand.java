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
        Ui.ShowLine();
        if (keyword.isBlank()) {
            Ui.PrintWithIndent("Sorry, I need a keyword.");
        } else {
            int count = 0;
            for (Task t: taskList.getList()) {
                if (t.getName().contains(keyword)) {
                    count++;
                    Ui.PrintWithIndent(count + "." + t.toString());
                }
            }
            Ui.PrintWithIndent("You have " + count + " matching task" + (count != 1 ? "s" : "")
                    + " for keyword: " + keyword);
        }
        Ui.ShowLine();
    }

    public boolean isExit() { return false; }
}
