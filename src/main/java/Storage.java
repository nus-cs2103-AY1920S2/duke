import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    // load file into TaskList and return it
    public TaskList getTaskList() throws IOException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            parseLine(s.nextLine(), taskList);
        }
        return taskList;
    }

    // helper method for getTaskList() to handle each line of file
    public void parseLine(String taskString, TaskList taskList) {
        String[] taskDetails = taskString.split(",");
        for (int i = 0; i < taskDetails.length; i++) {
            taskDetails[i] = taskDetails[i].trim();
        }
        String taskType = taskDetails[0];
        boolean isTaskDone = taskDetails[1].equals("1");
        String taskName = taskDetails[2];

        if (taskType.equals("T")) {
            Task newTask = new ToDo(taskName);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        } else if (taskType.equals("D")) {
            String taskTime = taskDetails[3];
            Task newTask = new Deadline(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        } else if (taskType.equals("E")) {
            String taskTime = taskDetails[3];
            Task newTask = new Event(taskName, taskTime);
            newTask.done = isTaskDone;
            taskList.arrList.add(newTask);
        }
    }

    // save the given taskList to the file
    public void updateFile(TaskList taskList) throws IOException {
        String textToAdd = "";
        for (Task t : taskList.arrList) {
            textToAdd += t.toFile() + "\n";
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

}
