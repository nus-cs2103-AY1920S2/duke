package duke.command;

import duke.main.UiHandler;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Delete command implementing command interface.
 */
public class DeleteCommand implements Command {

    /**
     * Attempts to delete task from task list and set ui response to the
     * appropriate one.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {

        String[] token = task.split(" ", 2);
        if (token.length < 2) {
            uiHandler.setResponse("Please specify which task to delete");
            return;
        }
        assert token[0].equals("delete");

        String[] indices = token[1].split(" ");
        if (!Utils.isNumeric(indices[0])) {
            uiHandler.setResponse("Please specify which task to delete");
            return;
        }

        List<Integer> toBeDeleted = new ArrayList<>();
        for (int i = 0; i < indices.length; i++) {
            if (Utils.isNumeric(indices[i]) != true) {
                continue;
            } else {
                toBeDeleted.add(Integer.parseInt(indices[i]) - 1);
            }
        }

        Collections.sort(toBeDeleted);

        if (toBeDeleted.size() < 1) {
            uiHandler.setResponse("Please specify which task to delete");
            return;
        }
        if (toBeDeleted.get(0) >= taskList.size() || toBeDeleted.get(toBeDeleted.size() - 1) < 0) {
            uiHandler.setResponse("No such task number");
            return;
        }

        String response = "";

        for (int i = toBeDeleted.size() - 1; toBeDeleted.size() > 0 && i >= 0; i--) {
            int taskId = toBeDeleted.get(i);
            if (taskId >= taskList.size() || taskId < 0) {
                continue;
            }
            if (response.equals("")) {
                response = taskList.getList().get(taskId) + response;
            } else {
                response = taskList.getList().get(taskId) + "\n " + response;
            }
            taskList.removeTask(taskId);
        }

        try {
            storage.storeData(Parser.tasksToStorage(taskList.getList()));
        } catch (IOException e) {
            uiHandler.setResponse("There is an when trying to store data");
            return;
        }

        response = "Nice! Deleted tasks(unknown task number ignored):\n " + response;
        uiHandler.setResponse(response);
    }

}
