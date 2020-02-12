package duke.command;

import duke.main.UiHandler;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.IOException;
import java.text.ParseException;

/**
 * Add command implementing command interface.
 */
public class AddCommand implements Command {

    /**
     * Executes the command by attempting to add task to list and set ui response to
     * the appropriate one.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        String[] token = task.split(" ", 2);
        if (token.length < 2) {
            uiHandler.setResponse("Adding task failed, task body cannot be empty");
            return;
        }
        assert token[0].equals("todo") || token[0].equals("event") || token[0].equals("deadline");
        try {
            if (taskList.addToList(token[1], token[0])) {
                try {
                    storage.storeData(Parser.tasksToStorage(taskList.getList()));
                } catch (IOException e) {
                    System.out.println("Error in storing data");
                }
                uiHandler.setResponse("I've added this task to the list:\n "
                        + taskList.getList().get(taskList.size() - 1) + "\n"
                        + "Now you have " + taskList.size() + " task(s) in the list");
                return;
            } else {
                uiHandler.setResponse("Adding task failed, either task body is empty "
                        + "or required time is not specified");
                return;
            }
        } catch (ParseException e) {
            uiHandler.setResponse("There is an internal error when parsing command");
            return;
        }
    }

}
