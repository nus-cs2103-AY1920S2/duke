package duchess.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import duchess.exception.DuchessException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The {@code Storage} class helps to save and load @{code ArrayList}s of
 * {@code Task}s from a given File Path.
 */
public class Storage {
    private String filePath;
    private Gson gson;

    /**
     * Initialises a {@code Storage} instance that works with the given
     * {@code filePath}.
     *
     * @param filePath The file path to save to and if possible, load from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.gson = new Gson();
    }

    /**
     * Saves a given list of tasks to the file path.
     *
     * @param tasks List of tasks to be saved.
     * @throws DuchessException If it fails to save to the file path.
     */
    public void save(TaskList tasks) throws DuchessException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            Task[] taskArray = tasks.getTaskArray().toArray(new Task[tasks.size()]);
            fileWriter.write(this.gson.toJson(taskArray, Task[].class));
            fileWriter.close();
        } catch (IOException e) {
            throw new DuchessException("Facing difficulties saving your tasks right now.");
        }
    }

    /**
     * Loads and returns a list of tasks from the file path.
     *
     * @return Loaded list of tasks from given file path.
     * @throws DuchessException If it fails to load from the file path.
     */
    public ArrayList<Task> load() throws DuchessException {
        try {
            String fileContent = Files.readString(Path.of(this.filePath));
            JsonArray array = JsonParser.parseString(fileContent).getAsJsonArray();
            ArrayList<Task> result = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonObject taskToCheck = (JsonObject) array.get(i);
                if (taskToCheck.has("deadline")) {
                    result.add(new Deadline(this.gson.fromJson(taskToCheck.get("description"), String.class),
                            this.gson.fromJson(taskToCheck.get("deadline"), LocalDateTime.class),
                            this.gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                } else if (taskToCheck.has("timeFrame")) {
                    result.add(new Event(this.gson.fromJson(taskToCheck.get("description"), String.class),
                            this.gson.fromJson(taskToCheck.get("timeFrame"), String.class),
                            this.gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                } else {
                    result.add(new ToDo(this.gson.fromJson(taskToCheck.get("description"), String.class),
                            this.gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                }
            }
            return result;
        } catch (IOException e) {
            String[] paths = this.filePath.split("/");
            for (int i = 0; i < paths.length - 1; i++) {
                File folder = new File(paths[i].trim());
                if (!folder.exists() || !folder.isDirectory()) {
                    boolean isDirectoryCreated = folder.mkdir();
                    if (isDirectoryCreated) {
                        paths[i + 1] = paths[i] + "/" + paths[i + 1];
                    } else {
                        throw new DuchessException("Failed to load save file! "
                                + "You will also not be able to save.");
                    }
                }
            }
            throw new DuchessException("Failed to load save file! Creating new save file.");
        }
    }
}
