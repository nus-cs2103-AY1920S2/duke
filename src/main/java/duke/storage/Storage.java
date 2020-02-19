package duke.storage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import duke.exception.DuchessException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringDeadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.Frequency;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static duke.util.MagicStrings.ERROR_FAIL_TO_LOAD;
import static duke.util.MagicStrings.ERROR_FAIL_TO_LOAD_AND_SAVE;
import static duke.util.MagicStrings.ERROR_FAIL_TO_SAVE;
import static duke.util.MagicStrings.GSON_ATTR_DEADLINE;
import static duke.util.MagicStrings.GSON_ATTR_DESCRIPTION;
import static duke.util.MagicStrings.GSON_ATTR_FREQUENCY;
import static duke.util.MagicStrings.GSON_ATTR_IS_COMPLETED;
import static duke.util.MagicStrings.GSON_ATTR_REPEAT_END_TIME;
import static duke.util.MagicStrings.GSON_ATTR_TIME_FRAME;

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
     * @throws IllegalArgumentException If {@code filePath} points to a non-json file. Storage
     *                                  defaults to "data/tasks.json".
     */
    public Storage(String filePath) throws IllegalArgumentException {
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
            throw new DuchessException(ERROR_FAIL_TO_SAVE);
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
            JsonArray jsonArray = readDataFromFilePath();
            return readTaskArrayFromJsonArray(jsonArray);
        } catch (IOException e) {
            if (!isAbleToSave()) {
                throw new DuchessException(ERROR_FAIL_TO_LOAD_AND_SAVE);
            }
            throw new DuchessException(ERROR_FAIL_TO_LOAD);
        }
    }

    // Private helper methods

    private JsonArray readDataFromFilePath() throws IOException {
        String fileContent = Files.readString(Path.of(this.filePath));
        return JsonParser.parseString(fileContent).getAsJsonArray();
    }

    private ArrayList<Task> readTaskArrayFromJsonArray(JsonArray jsonArray) {
        ArrayList<Task> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject taskToCheck = (JsonObject) jsonArray.get(i);
            if (taskToCheck.has(GSON_ATTR_FREQUENCY) && taskToCheck.has(GSON_ATTR_DEADLINE)) {
                // Task is a RecurringDeadline
                if (taskToCheck.has(GSON_ATTR_REPEAT_END_TIME)) {
                    result.add(new RecurringDeadline(
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_DESCRIPTION), String.class),
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_DEADLINE), LocalDateTime.class),
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_FREQUENCY), Frequency.class),
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_REPEAT_END_TIME), LocalDateTime.class)
                    ));
                } else {
                    result.add(new RecurringDeadline(
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_DESCRIPTION), String.class),
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_DEADLINE), LocalDateTime.class),
                            this.gson.fromJson(taskToCheck.get(GSON_ATTR_FREQUENCY), Frequency.class)
                    ));
                }
            } else if (taskToCheck.has(GSON_ATTR_DEADLINE)) {
                // Task is a Deadline
                result.add(new Deadline(this.gson.fromJson(taskToCheck.get(GSON_ATTR_DESCRIPTION), String.class),
                        this.gson.fromJson(taskToCheck.get(GSON_ATTR_DEADLINE), LocalDateTime.class),
                        this.gson.fromJson(taskToCheck.get(GSON_ATTR_IS_COMPLETED), boolean.class)));
            } else if (taskToCheck.has(GSON_ATTR_TIME_FRAME)) {
                // Task is an Event
                result.add(new Event(this.gson.fromJson(taskToCheck.get(GSON_ATTR_DESCRIPTION), String.class),
                        this.gson.fromJson(taskToCheck.get(GSON_ATTR_TIME_FRAME), String.class),
                        this.gson.fromJson(taskToCheck.get(GSON_ATTR_IS_COMPLETED), boolean.class)));
            } else {
                // Task is a ToDo
                result.add(new ToDo(this.gson.fromJson(taskToCheck.get(GSON_ATTR_DESCRIPTION), String.class),
                        this.gson.fromJson(taskToCheck.get(GSON_ATTR_IS_COMPLETED), boolean.class)));
            }
        }
        return result;
    }

    private boolean isAbleToSave() throws DuchessException {
        File file = new File(this.filePath);
        File directories = file.getParentFile();
        return directories.exists() || directories.mkdirs();
    }
}
