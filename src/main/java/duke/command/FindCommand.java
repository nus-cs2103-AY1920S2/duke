package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    public String execute(Storage storage, TaskList taskList) throws IOException {
        String message = "Here are the matching tasks in your list: "
                + System.lineSeparator()
                + taskList.searchTaskList(keyword);
        Ui.printMessage(message);
        return message;
    }

}
