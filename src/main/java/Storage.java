import exception.DukeException;
import tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    TaskList taskList = new TaskList();
    private File file = new File("data/duke.txt");

    /**
     * Loads tasks stored in duke.txt in the hard disk. Creates and add the tasks into the ArrayList of tasks.
     * @throws IOException Throws IOException.
     * @throws DukeException Throws DukeException.
     */
    public void loadData() throws IOException, DukeException {
        // load data from ./data/duke.txt
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            while ((str = br.readLine()) != null) {
                Task task;
                String[] data = str.split("\\|");
                if (data[0].equals("T")) {
                    task = taskList.createAndAddTask("todo", "todo " + data[2]);
                } else if (data[0].equals("E")){
                    task = taskList.createAndAddTask("event", "event " + data[2] + " /at " + data[3]);
                } else {
                    task = taskList.createAndAddTask("deadline", "deadline " + data[2] + " /by " + data[3]);
                }

                if (data[1].equals("1")) {
                    task.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            Path path = Paths.get("data/duke.txt");
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
    }

    /**
     * Saves the tasks in duke.txt in the hard disk.
     * @throws IOException Throws IOException.
     */
    public void saveData() throws IOException {
        if (taskList.isListEmpty()) {
            Files.write(Paths.get("./data/duke.txt"), ("").getBytes());
        } else {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < taskList.numOfTasks(); i++) {
                bw.write(taskList.getTask(i).saveString());
                bw.newLine();
            }
            bw.close();
        }
    }

    /**
     * Saves new tasks by adding a line in duke.txt instead of re-writing everything.
     * It will allow auto-save.
     * @param task The newly added task.
     * @throws IOException Throws IOException.
     */
    public void saveNewTask(Task task) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("./data/duke.txt", true));
        bw.write(task.saveString());
        bw.newLine();
        bw.close();
    }

    /**
     * Deletes tasks from the disk by index.
     * @param index The index of the task to be deleted in the task list.
     */
    public void deleteTask(int index) throws IOException {
        String newFileData = "";

        BufferedReader reader = new BufferedReader(new FileReader(file));

        int counter = 0;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if (counter == index) {
                counter++;
            } else {
                counter++;
                newFileData = newFileData + currentLine + "\n";
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(newFileData);
        writer.close();
    }

    /**
     * Marks the task in the hard disk as done.
     * @param index The index of the task to be marked done in the task list.
     */
    public void doneTask(int index) throws IOException {
        String newFileData = "";

        BufferedReader reader = new BufferedReader(new FileReader(file));

        int counter = 0;
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if (counter == index) {
                counter++;
                newFileData = newFileData + currentLine.substring(0, 2) + "1" + currentLine.substring(3) + "\n";
            } else {
                counter++;
                newFileData = newFileData + currentLine + "\n";
            }
        }
        reader.close();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(newFileData);
        writer.close();
    }
}
