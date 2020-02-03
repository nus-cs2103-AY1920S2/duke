package duke.command;

import duke.main.Ui;
import duke.utils.Parser;
import duke.utils.FileStorage;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.IOException;
import java.text.ParseException;

/**
 * Add command implementing command interface
 */
public class AddCommand implements Command {

    /**
     * execute the command by attempting to add task to list and set ui response to
     * the appropriate one
     * @param task
     * @param ui
     * @param storage
     * @param taskList
     */
    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        String[] token = task.split(" ", 2);
        if (token.length < 2) {
            ui.setResponse("Adding task failed, task body cannot be empty");
            return;
        }
        try {
            if (taskList.addToList(token[1], token[0])) {
                try {
                    storage.storeData(Parser.tasksToStorage(taskList.getList()));
                } catch (IOException e) {
                    System.out.println("Error in storing data");
                }
                ui.setResponse("I've added this task to the list:\n " + taskList.getList().get(taskList.size() - 1) + "\n" +
                        "Now you have " + taskList.size() + " task(s) in the list");
                return;
            } else {
                ui.setResponse("Adding task failed, either task body is empty or required time is not specified");
                return;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
