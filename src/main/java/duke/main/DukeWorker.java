package duke.main;

import duke.command.Command;
import duke.task.Task;
import duke.utils.CommandParser;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.io.IOException;
import java.util.List;

public class DukeWorker {

    private Storage storage;
    private TaskList taskList;

    public DukeWorker(String storagePath) {
        storage = new Storage(storagePath);
        taskList = new TaskList();
    }

    public void initializeWorker() {
        try {
            boolean fileExist = storage.checkFileExistence();
            if (!fileExist) {
                storage.createStorage();
            } else {
                String data = storage.loadStorage();
                List<Task> tasks = Parser.storageToTask(data);
                taskList = new TaskList(tasks);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void handleRequest(String request, Ui ui) {
        Command command = CommandParser.commandParser(request);
        command.execute(request, ui, storage, taskList);
    }
}
