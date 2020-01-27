import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private File data;

    public Storage(String s) {
        try {
            this.data = new File(s);
        } catch(Exception e) {
            System.err.println("Error retrieving file!");
        }
    }

    public List<Task> buildTaskList() {
        List<Task> taskList = new ArrayList<>();
        Scanner scan = new Scanner(data);
        while(scan.hasNext()) {
            String elementLine = scan.nextLine();
            String[] elements = elementLine.split("\|");
            switch(elements[0]) {
            case "T":
                taskList.add(Todo.create(elements));
                break;
            case "E":
                taskList.add(Event.create(elements));
                break;
            case "D":
                taskList.add(Deadline.create(elements));
                break;
            default:
                taskList.add(Task.create(elements));
                break;
            }
            
            return taskList;
        }
    }

    public void updateStorage(List<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(data);
            for (Task t: taskList) {
                writer.write(t.store() + "\n");
            }
            writer.close();
        } catch(Exception e) {
            System.err.println("Error writing to save file, message: " + e.toString());
        } finally {
            return;
        }
    }
}
