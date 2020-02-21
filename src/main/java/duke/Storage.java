/**
 * The Storage class handles all I/O in the software with two key methods, load() and save(). The load() method
 * handles loading of any existing saved data into the TaskList object, while the save() method handles saving the most
 * recent version of the TaskList object when the method is called.
 */

package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Storage {
    static final File savedData = new File("data/duke.txt");
    static final File thisLoc = new File(System.getProperty("user.dir") + "/data");
    static final String delimiter = " ~ ";

    /**
     * This method handles loading of an existing data file stored in the directory 'data', where the program is
     * located, and populate a new TaskList object.
     * If the directory is absent, a new directory is created.
     * If the directory is created and the data file is absent, a new data file is created.
     * @return a new TaskList object.
     */
    public static TaskList load() {
        TaskList tasklist = new TaskList();

        try {
            // Checks if the directory exists, and creates it if it does not exist.
            thisLoc.mkdir();

            // Checks if the file exists, and creates it if it does not exist.
            savedData.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(savedData));
            System.out.println("Loading file 'duke.txt'... ");

            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                    String[] taskContent = line.split(delimiter);
                    boolean isDone = taskContent[1].equals("1");

                    if (taskContent[0].equals("T")) {
                        tasklist.newTodo(isDone, taskContent[2]);
                    } else if (taskContent[0].equals("D")) {
                        tasklist.newDeadline(isDone, taskContent[2], taskContent[3]);
                    } else if (taskContent[0].equals("E")) {
                        tasklist.newEvent(isDone, taskContent[2], taskContent[3]);
                    }

                } catch (IOException e) {
                    System.out.println("Oops! Unable to read save file due to " + e + "!");
                    break;
                }

            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Oops! Unable to create or load save file due to " + e + "!");
        }

        return tasklist;
    }

    /**
     * This method saves a current TaskList object into the data file in the directory 'data', where the program
     * is located, assuming that the file has already been created before or during the launch of the program.
     * @param tasklist the TaskList object to parse into the data file.
     */
    public static void save(TaskList tasklist) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(savedData));
        String delimiter = " ~ ";

        for (Task thisTask : tasklist.getList()) {
            String taskStr = thisTask.getTaskType() + delimiter
                    + (thisTask.isDone() ? "1" : "0") + delimiter
                    + thisTask.getTaskName() + delimiter
                    + (thisTask instanceof Todo ? "" : thisTask.getTaskDateTime()) + "\n";
            writer.write(taskStr);
        }
        writer.close();
    }

}
