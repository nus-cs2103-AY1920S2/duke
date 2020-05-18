import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1> Storage </h1>
 * The Storage class handles the loading of tasks from the disk and saving tasks to the disk.
 * This class has two methods: load() and save().
 * <p> Storage contains a String attribute "filePath" which contains the filepath of the file that stores the data</p>
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * this method loads the data from the storage file and returns an arraylist containing the details of the tasks
     * stored
     * @return ArrayList of the stored tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] splitStr = str.split("\\s+" + "\\|" + "\\s+");
                String taskType = splitStr[0];
                String taskStatus = splitStr[1];
                if (taskType.equals("T")) {
                    ToDo t = new ToDo(splitStr[2]);
                    tasks.add(t);
                } else if (taskType.equals("E")) {
                    LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                    Event t = new Event(splitStr[2], dateTime);
                    tasks.add(t);
                } else if (taskType.equals("D")) {
                    LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                    Deadline t = new Deadline(splitStr[2], dateTime);
                    tasks.add(t);
                }
                if (taskStatus.equals("1")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    /**
     * this method rewrites the storage file with the latest list of tasks
     * @param taskList the latest tasklist
     * @throws IOException thrown when there is an error with the file
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.write(t.toFileString() + "\n");
        }
        fw.close();
    }
}
