package duke.main;

import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import duke.exception.UnableToLoadException;
import duke.exception.UnableToSaveException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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

                for (int i = 0; i < lines.size(); i++) {
                    addTaskFromStorage(tasks, lines, i);
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
     * Saves current tasks in taskslist into local storage. 
     * The saved tasks can be retrieved when program starts again.
     * 
     * @param tasks List of tasks to be saved
     * @throws UnableToLoadException If cannot save into specified directory.
     */
    public void saveToSave(TaskList tasks) throws UnableToSaveException {
        String content = getTasksInSaveFormat(tasks);
        try {
            Files.writeString(path, content);
        } catch (IOException e) {
            throw new UnableToSaveException();
        }
    }

    private String getTasksInSaveFormat(TaskList tasks) {
        String content = "";
        for (int i = 0; i < tasks.size(); i++) {
            content += (tasks.getTask(i).storeFormat() + "\n");
        }
        return content;
    }

    /**
     * Adds task to list of tasks. The type of tasks can be deadline, todo, event.
     * 
     * @param tasks List of tasks to add to
     * @param entries All the entries that are found in storage
     * @param index The entry to add to list
     */
    private void addTaskFromStorage(List<Task> tasks, List<String> entries, int index) {
        String entry = entries.get(index);
        String[] entryAttributes = entry.split("\\| \\|");
        String type = entryAttributes[0];
        boolean completed = (entryAttributes[1].equals("true"));
        String name = entryAttributes[2];

        switch (type) {
        case "E":
            assert entryAttributes.length == 4 : "Entry of event should have E| |isCompleted| |name| |date and time";
            String dateTime = entryAttributes[3];
            tasks.add(new Event(name, completed, dateTime));
            break;
        case "D":
            assert entryAttributes.length == 4 : "Entry of deadline should have D| |isCompleted| |name| |date";
            String date = entryAttributes[3];
            tasks.add(new Deadline(name, completed, date));
            break;
        case "T":
            assert entryAttributes.length == 4 : "Entry of todo should have T| |isCompleted| |name";
            tasks.add(new Todo(name, completed));
            break;
        default:
        }
    }
}