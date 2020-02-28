package duke;

import duke.exception.DukeException;
import duke.exception.DukeExceptionLoad;
import duke.exception.DukeExceptionSave;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;

/**
 * Loads and Stores the TaskList to Path.
 */
public class Storage {

    private Path path;

    public Storage(Path path) throws DukeException {
        this.path = path;
    }

    /**
     * Loads the tasks from Path into TaskList.
     * @return Updated TaskList
     * @throws DukeException Error resulting from loading Tasks.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.write(path, new ArrayList<String>(), StandardCharsets.UTF_8);

            } catch (IOException e) {
                throw new DukeExceptionLoad("path");
            }
        }

        try {
            List<String> lines = Files.readAllLines(path);
            for (String curLine : lines) {
                String[] components = curLine.split(" , ");

                if (components.length < 3) {
                    continue;
                }

                String commandType = components[0];
                Task newTask;
                switch (commandType) {
                case "T":
                    newTask = new ToDo(" " + components[2]);
                    newTask.setStatus(components[1]);
                    tasks.addTask(newTask);
                    break;

                case "D":
                    newTask = new Deadline(" " + components[2], LocalDate.parse(components[3]));
                    newTask.setStatus(components[1]);
                    tasks.addTask(newTask);
                    break;

                case "E":
                    newTask = new Event(" " + components[2], LocalDate.parse(components[3]));
                    newTask.setStatus(components[1]);
                    tasks.addTask(newTask);
                    break;

                default:
                    throw new DukeExceptionLoad("format");
                }
            }
        } catch (IOException error) {
            throw new DukeExceptionLoad("filetype");
        }
        return tasks;
    }

    /**
     * Saves the current TaskList into Path.
     * @param tasks Current TaskList.
     * @throws DukeException Error from saving Tasks.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            String savedString = tasks.saveFormat();
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write(savedString);
            writer.flush();
        } catch (IOException error) {
            throw new DukeExceptionSave();
        }
    }

}
