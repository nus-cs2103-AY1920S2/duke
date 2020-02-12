package duke;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads the tasks data from the file.
     * @return A list of tasks.
     * @throws DukeException Error when reading the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(" \\| ");
                boolean isDone = !details[1].equals("0");
                String description = details[2];

                switch (details[0]) {
                case "T":
                    tasks.add(new Todo(isDone, description));
                    break;
                case "D":
                    tasks.add(new Deadline(isDone, description,
                            LocalDateTime.parse(details[3])));
                    break;
                case "E":
                    tasks.add(new Event(isDone, description,
                            LocalDateTime.parse(details[3])));
                    break;
                default:
                    assert false : "Invalid save file format.";
                    continue;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Problem with reading save file.");
        }

        return tasks;
    }

    /**
     * Saves the tasks to the given file path.
     * @param tasks The tasks to be saved.
     * @throws DukeException Error when writing to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        // remove existing file
        if (file.exists()) {
            file.delete();
        }

        // create directory parent directory of file if 
        // it does not exist
        new File(file.getParent()).mkdirs();

        try {
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(tasks.toSaveFormat());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Problem writing into save file.");
        }
    }
}