package duke.command;

import duke.main.UiHandler;
import duke.task.Task;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Done command implementing command interface.
 */
public class SnoozeCommand implements Command {

    /**
     * Attempts to delay the deadline or schedule of selected task and set ui response to
     * the appropriate one.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        String[] token = task.split(" ");
        if (token.length != 3) {
            uiHandler.setResponse("Please only specify which task to snooze and for how many days");
            return;
        }
        assert token[0].equals("snooze");
        if (!Utils.isNumeric(token[1]) || !Utils.isNumeric(token[2])) {
            uiHandler.setResponse("Only numbers accepted for task number and delay");
            return;
        }
        int taskId = Integer.parseInt(token[1]) - 1;
        int delay = Integer.parseInt(token[2]);
        Task selectedTask = taskList.getList().get(taskId);
        String response;
        if (selectedTask.getTaskTime() == null) {
            response = "Selected task does not have deadline or schedule";
        } else {
            LocalDate newTaskTime = selectedTask.getTaskTime().plusDays(delay);
            selectedTask.setTaskTime(newTaskTime);
            response = "Task schedule or deadline updated:\n " + taskList.getList().get(taskId);
            try {
                storage.storeData(Parser.tasksToStorage(taskList.getList()));
            } catch (IOException e) {
                uiHandler.setResponse("There is an error when trying to store data");
                return;
            }
        }
        uiHandler.setResponse(response);
    }
}
