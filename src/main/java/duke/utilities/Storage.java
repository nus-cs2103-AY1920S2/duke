package duke.utilities;

import duke.tasks.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Storage class to load and add tasks to tasks.txt.
 */
public class Storage {
    String path;
    Parser parser;

    /**
     * Constructor for Storage.
     *
     * @param path the String representation of the path to tasks.txt, passed to Storage in Duke class.
     */
    public Storage(String path) {
        this.path = path;
        this.parser = new Parser();
    }

    /**
     * Loads all tasks from the hard disk into an ArrayList of tasks.
     *
     * @return ArrayList of all tasks previously stored in the hard disk.
     */
    public ArrayList<Task> load() { // load all tasks from hard disk into an ArrayList of tasks
        ArrayList<Task> lst = new ArrayList<>();
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("[^\\x00-\\x7F]", "");
                lst.add(Parser.parseFile(line));
            }


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
    public void update(ArrayList<Task> lst) { // update the file in the hard disk whenever the task list changes
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            FileOutputStream outputStream = new FileOutputStream(path);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);

            for (Task task : lst) {
                bw.write(Parser.parseTask(task));
                bw.newLine();
                bw.flush();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
