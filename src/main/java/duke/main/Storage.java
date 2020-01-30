package duke.main;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import duke.exception.UnableToLoadException;
import duke.exception.UnableToSaveException;
import duke.task.*;

public class Storage {
    private Path path;

    public Storage(String location) {
        this.path = Paths.get(location, "data", "duke.txt");
    }

    public void retryLocation(String location) {
        this.path = Paths.get(location, "data", "duke.txt");
    }

    /**
     * Returns tasklist generated from the information stored in local storage.
     * Done at start of the program in order to retrieve the state from the previous
     * session.
     * 
     * @return List of tasks found in local storage
     * @throws UnableToLoadException If cannot access specified directory.
     */
    public List<Task> loadFromSave() throws UnableToLoadException {
        List<Task> tasks = new ArrayList<>();
        try {
            if (Files.exists(path)) {
                List<String> lines = Files.readAllLines(path);
                String outputLine;
                String[] arr;
                String type;
                boolean completed;
                String name;
                String others;

                for (int i = 0; i < lines.size(); i++) {
                    outputLine = lines.get(i);
                    arr = outputLine.split("\\| \\|");
                    type = arr[0];
                    completed = (arr[1].equals("true"));
                    name = arr[2];

                    switch (type) {
                    case "E":
                        others = arr[3];
                        tasks.add(new Event(name, completed, others));
                        break;
                    case "D":
                        others = arr[3];
                        tasks.add(new Deadline(name, completed, others));
                        break;
                    case "T":
                        tasks.add(new Todo(name, completed));
                        break;
                    default:
                    }
                }
            } else {
                Files.createDirectory(Paths.get("data"));
                Files.createFile(path);
            }
        } catch (IOException e) {
            throw new UnableToLoadException();
        }
        return tasks;
    }

    /**
     * This method saves current tasks in taskslist into local storage. The saved
     * tasks can be retrieved when program starts again.
     * 
     * @param tasks List of tasks to be saved
     * @throws UnableToLoadException If cannot save into specified directory.
     */
    public void saveToSave(TaskList tasks) throws UnableToSaveException {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += (tasks.getTask(i).storeFormat() + "\n");
        }
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new UnableToSaveException();
        }
    }

}