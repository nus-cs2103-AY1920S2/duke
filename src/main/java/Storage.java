import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <h1> Storage </h1>
 * The Storage class handles the loading of tasks from the disk and saving tasks to the disk. The Storage class contains
 * a TaskList object as its attribute. This class has two methods: load() and save().
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {

        this.filePath = filePath;
    }

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

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.write(t.toFileString() + "\n");
        }
        fw.close();
    }
}
