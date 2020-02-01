import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static String tasksToStorage(List<Task> tasks) {
        String data = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String doneString = task.isDone()? "1" : "0";
            data = data + task.getTaskType() + "&" + doneString + "&" + task.getTaskName();
            if (task.getTaskType() == "E" || task.getTaskType() == "D") {
                data = data + "&" + task.getTaskTime();
            }
            if (i < tasks.size() - 1) {
                data = data + "\n";
            }
        }
        return data;
    }

    public static List<Task> storageToTask(String data) {
        List<Task> tasks= new ArrayList<>();
        String[] lines = data.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] tokens = line.split("&");
            Task task = null;
            if (tokens[0].equals("T")) {
                task = new Todos(tokens[2]);
                if (tokens[1].equals("1")) {
                    task.markAsDone();
                }
            } else if (tokens[0].equals("E")) {
                task = new Events(tokens[2], tokens[3]);
            } else if (tokens[0].equals("D")) {
                task = new Deadlines(tokens[2], tokens[3]);
            }
            if (task != null && tokens[1].equals("1")) {
                task.markAsDone();
            }
            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

}
