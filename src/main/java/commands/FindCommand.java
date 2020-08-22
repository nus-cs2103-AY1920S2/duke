package commands;

import storage.Storage;
import exceptions.DukeException;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private String key;

    public FindCommand(String key) {
        this.key = key;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String response = ui.getFindResponse(key, taskList);
            System.out.println(response);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
