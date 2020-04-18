package duke;
import duke.exceptions.DukeIOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * A storage class that manages the accessing of all the tasks from the database.
 */
public class Storage {

    static private String path;

    /**
     * Constructs a Storage.
     * @param path the path that points to the location of the database, a text file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads all the previous tasks from the database into a temporary list.
     * @return The list after the tasks are loaded.
     * @throws DukeIOException The database is not found or a wrong location.
     */
    public List<Task> load() throws DukeIOException {
        List<Task> storedTasks = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(this.path));
            Parser parser = new Parser();
            String line = bfr.readLine();
            while (line != null) {
                parser.processInputFromFile(line, storedTasks);
                line = bfr.readLine();
            }
        } catch (IOException io) {
            throw new DukeIOException();
        } finally {
            return storedTasks;
        }
    }

    /**
     * Uploads the updated list of all files to the database upon Duke's termination.
     * @param toStore The list of tasks that needs to be stored into the database.
     */
    public static void write(List<Task> toStore) {
        try {
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < toStore.size(); i++) {
                Task curr = toStore.get(i);
                if (curr instanceof Deadline) {
                    fw.write("D | ");
                    fw.write((curr.getStatus() ? "1" : "0") + " | ");
                    fw.write(curr.getDescriptionWithoutIcon() + " | ");
                    fw.write(((Deadline) curr).getDeadline());
                } else if (curr instanceof Event) {
                    fw.write("E | ");
                    fw.write((curr.getStatus() ? "1" : "0") + " | ");
                    fw.write(curr.getDescriptionWithoutIcon() + " | ");
                    fw.write(((Event) curr).getTime());
                } else {
                    fw.write("T | ");
                    fw.write((curr.getStatus() ? "1" : "0") + " | ");
                    fw.write(curr.getDescriptionWithoutIcon());
                }
                if (i != toStore.size() - 1) {
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}