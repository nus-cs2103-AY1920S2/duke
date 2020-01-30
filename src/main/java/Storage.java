import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task: tasks.getTasks()) {
            fw.write(task.toFormatString() + "\n");
        }
        fw.close();
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            char taskType = line.charAt(0);
            char taskCondition = line.charAt(4);
            String taskContent = line.substring(8);
            String[] taskAndTime = taskContent.split("\\| ");
            if (taskType == 'T') {
                taskList.add(new Todo(taskContent));
            } else if (taskType == 'D') {
                taskList.add(new Deadline(taskAndTime[0], LocalDateTime.parse(taskAndTime[1])));
            } else if (taskType == 'E') {
                taskList.add(new Event(taskAndTime[0], LocalDateTime.parse(taskAndTime[1])));
            }

            if (taskCondition == '1') {
                taskList.get(taskList.size() - 1).finishTask();
            }
        }
        return taskList;
    }
}
