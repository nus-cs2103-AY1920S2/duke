package duke.utilities;

import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Storage class to load and add tasks to tasks.txt.
 */
public class Storage {
    private String path = "tasks.txt";
    /**
     * Constructor for Storage.
     */
    public Storage() {

    }

    /**
     * Loads all tasks from the hard disk into an ArrayList of tasks.
     *
     * @return ArrayList of all tasks previously stored in the hard disk.
     */
    public ArrayList<Task> load() throws DukeException { // load all tasks from hard disk into an ArrayList of tasks
        ArrayList<Task> lst = new ArrayList<>();
        try {
            File file = this.getFile();

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[^\\x00-\\x7F]", ""); // remove any unwanted ascii characters
                lst.add(Parser.parseFile(line));
            }
            br.close();

        } catch (IOException e) {
            System.err.println(e);
        }
        return lst;
    }

    /**
     * Updates the file in the hard disk whenever the task list changes.
     * Called in execute method of Command.
     *
     * @param lst the updated ArrayList of tasks, to be parsed into the hard disk.
     */
    public void update(ArrayList<Task> lst) throws DukeException { // update the file in the hard disk whenever the task list changes
        try {
            File file = this.getFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);

            for (Task task : lst) {
                bw.write(Parser.parseTask(task));
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getFile() throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }
}
