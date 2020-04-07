package duke.command;

import duke.main.UiHandler;
import duke.task.Task;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.util.List;

/**
 * Find Command which implements Command interface.
 */
public class FindCommand implements Command {

    /**
     * Attempts to find task list based on key word and set ui response
     * to display tasks found.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        String[] token = task.split(" ");

        if (token.length < 2) {
            uiHandler.setResponse("Please specify a keyword to search for");
            return;
        }
        assert token[0].equals("find");

        if (token.length > 2) {
            uiHandler.setResponse("Only 1 keyword is allowed");
            return;
        }

        List<Task> searchedTask = taskList.findTasksByKeyword(token[1]);

        String response = "";

        if (searchedTask.size() == 0) {
            response = "No task with that keyword found";
        } else {
            response = "Here are the matching tasks in your list:";
            for (int i = 0; i < searchedTask.size(); i++) {
                response = response + "\n " + (i + 1) + ". " + searchedTask.get(i).toString();
            }
        }

        uiHandler.setResponse(response);
    }

}
