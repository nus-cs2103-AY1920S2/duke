package duke.util;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    private File saveFile;

    /**
     * Storage's constructor checks if
     * "data/userdata.txt" exists. If not,
     * it creates the relevant directory and file.
     * Finally, initializes saveFile with that file.
     */
    public Storage() {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }
        saveFile = new File(dir, "userdata.txt");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * Getter for a reference to the File data with user data.
     * @return the File object representing user data
     */
    public File getSave() {
        return this.saveFile;
    }

    /**
     * Writes the information of a single task to the
     * save file (For single task additions by user).
     * @param task a reference to a Task object to save
     * @return void
     */
    public void saveTaskToFile(Task task) {
        FileWriter writer;
        try {
            writer = new FileWriter(saveFile, true);
            PrintWriter pw = new PrintWriter(writer);
            pw.println(task.toSaveString());
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Traverses whole list of tasks from TaskList object and
     * writes out to the save file.
     * @param taskList a reference to the TaskList object to read tasks from
     * @return void
     */
    public void saveTaskListToFile(TaskList taskList) {
        FileWriter writer;
        PrintWriter pw;
        try {
            // Clear save
            writer = new FileWriter(saveFile);
            pw = new PrintWriter(writer);
            pw.print("");
            // Save from scratch
            writer = new FileWriter(saveFile, true);
            pw = new PrintWriter(writer);
            pw.print("");
            for (Task t: taskList.getList()) {
                pw.println(t.toSaveString());
            }
            pw.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    /**
     * Reads through the save file and adds in any existing tasks into
     * a task list object.
     * @param taskList a reference to the TaskList object to add tasks to
     * @return void
     */
    public void load(TaskList taskList) {
        try {
            Scanner reader = new Scanner(saveFile);
            String data;
            while (reader.hasNextLine()) {
                data = reader.nextLine();
                taskList.addSaveStringAsTask(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
