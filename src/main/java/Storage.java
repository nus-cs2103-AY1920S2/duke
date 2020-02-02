import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;

public class Storage {
    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    public void load() throws FileNotFoundException {
        String filePath = "Data/Duke.txt";
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] splitStr = str.split("\\s+" + "\\|" + "\\s+");
            String taskType = splitStr[0];
            String taskStatus = splitStr[1];
            if (taskType.equals("T")) {
                ToDo t = new ToDo(splitStr[2]);
                this.taskList.addTask(t);
            } else if (taskType.equals("E")) {
                LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                Event t = new Event(splitStr[2], dateTime);
                this.taskList.addTask(t);
            } else if (taskType.equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                DeadLine t = new DeadLine(splitStr[2], dateTime);
                this.taskList.addTask(t);
            }
            if (taskStatus.equals("1")) {
                this.taskList.get(taskList.size() - 1).markAsDone();
            }
        }
    }

    public void save() throws IOException {
        String filePath = "Data/Duke.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.write(t.toFileString() + "\n");
        }
        fw.close();
    }
}
