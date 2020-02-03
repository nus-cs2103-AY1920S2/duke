package duke.command;

import duke.main.Ui;
import duke.utils.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {

        String[] token = task.split(" ", 2);
        if (token.length < 2) {
            ui.setResponse("Please specify which task to delete");
            return;
        }

        String[] indices = token[1].split(" ");
        if (!Utils.isNumeric(indices[0])) {
            ui.setResponse("Please specify which task to delete");
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
            ui.setResponse("Please specify which task to delete");
            return;
        }
        if (toBeDeleted.get(0) >= taskList.size() || toBeDeleted.get(toBeDeleted.size() - 1) < 0) {
            ui.setResponse("No such task number");
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
            System.out.println("Error in storing data");
        }

        response = "Nice! Deleted tasks(unknown task number ignored):\n " + response;
        ui.setResponse(response);
    }

}
