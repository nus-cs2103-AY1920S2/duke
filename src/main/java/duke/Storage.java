package duke;

import duke.exception.DukeException;

import duke.parser.StorageParser;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    /** Relative directory of the save file for this storage object. */
    private String filePath;

    /**
     * Constructs a new saving/loading mechanism.
     *
     * @param filePath the relative directory of the save file for this storage object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks generated from a save file.
     *
     * @return a list of tasks generated from a save file.
     * @throws DukeException if file could not be found or opened.
     */
    public List<Task> load() throws DukeException {
        try {
            File saveFile = new File(filePath);
            Scanner sc = new Scanner(saveFile);

            List<Task> tasks = new ArrayList<>();

            while (sc.hasNextLine()) {
                try {
                    Task task = StorageParser.readTask(sc.nextLine());
                    tasks.add(task);
                } catch (DukeException e) {
                    throw new DukeException("I can't read everything in the file!");
                }
            }

            return tasks;

        } catch (IOException e) {
            throw new DukeException("I can't write to this file!: " + filePath);
        }
    }

    /**
     * Writes a list of tasks into a save file.
     *
     * @param tasks the list of tasks to write to a save file.
     * @throws DukeException if file could not be found or opened.
     */
    public void save(TaskList tasks) throws DukeException {
        // Setup file output resources with auto-closing
        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 1; i <= tasks.getNumTasks(); i++) {
                // Each task has their own save file format
                fw.write(tasks.get(i).serialize());
                fw.append('\n');
            }

            fw.close();

        } catch (IOException e) {
            throw new DukeException("I can't write to this file!: " + filePath);
        }
    }
}