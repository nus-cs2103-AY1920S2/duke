package duke.main;

import duke.command.Command;
import duke.task.Task;
import duke.utils.CommandParser;
import duke.utils.Parser;
import duke.utils.FileStorage;
import duke.utils.TaskList;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class DukeWorker {

    private FileStorage storage;
    private TaskList taskList;

    public FileStorage getStorage() {
        return storage;
    }

    public DukeWorker(String storagePath) {
        storage = new FileStorage(storagePath);
        taskList = new TaskList();
    }

    /**
     * Initialize DukeWorker instance and load file storage.
     */
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
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void handleRequest(String request, UiHandler uiHandler) {
        Command command = CommandParser.commandParser(request);
        command.execute(request, uiHandler, storage, taskList);
    }
}
