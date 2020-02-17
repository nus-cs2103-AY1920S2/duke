package duke.command;

import duke.other.Storage;
import duke.other.Ui;
import duke.task.TaskList;

import java.io.File;

public class ByeCommand extends Command {
    private Storage storage = new Storage("data" + File.separator + "duke.txt");

    public ByeCommand() {
    }

    /**
     * Handles the Bye command.
     * @param taskList Overall TaskList of all the Tasks
     * @param ui       Overall Ui handling the ui of Duke
     * @return Returns the response of the bot to this command
     */
    public String execute(TaskList taskList, Ui ui) {
        isExit = true;
        storage.saveFile(taskList);
        System.out.println("    Bye! See ya later, alligator!");
        return "Bye! See ya later, alligator!";
    }
}
