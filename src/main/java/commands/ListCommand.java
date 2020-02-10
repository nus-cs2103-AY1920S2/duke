package commands;

import tasks.Task;
import exceptions.DukeException;
import storage.Storage;
import ui.Ui;
import tasklist.TaskList;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class ListCommand extends Command {

    public ListCommand(String command) {
        super(command);
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        System.out.println("Here are the tasks in your list:");
        List<Task> list = tasks.getList();
        ui.printList(list);
    }
}