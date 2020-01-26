import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskWriter {
    public void writeTasks(String filePath, ArrayList<Task> arrTasks) throws IOException {
        this.writeToFile(filePath, this.parseTasks(arrTasks));
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private String parseTask(Task t) {
        String taskString = t.getType() + " | ";
        if (t.isDone())
            taskString += "1 | ";
        else
            taskString += "0 | ";
        taskString += t.getDescription();
        if (t instanceof DateTimeTask)
            taskString += " | " + ((DateTimeTask) t).getDateTime();
        taskString += "\n";
        return taskString;
    }

    private String parseTasks(ArrayList<Task> arrTasks) {
        String allTasks = "";
        for (Task t: arrTasks) {
            allTasks += parseTask(t);
        }
        return allTasks;
    }
}