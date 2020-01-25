import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public TaskList(){
        tasks = new ArrayList<>();
    }

    public String toStringDukeTasks() {
        String taskString = "";
        for (Task task : tasks) {
            if (task instanceof Todo) {
                taskString += "T/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "\n";
            }
            else if (task instanceof Deadline) {
                taskString += "D/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "/" + ((Deadline) task).date.toString() + "\n";
            }
            else {               //instance of event
                taskString += "E/" + task.getStatusIconInBin() + "/";
                taskString += task.description + "/" + ((Event) task).date.toString() + "\n";
            }
        }
        return taskString;
    }

    public void add(Task task){
        tasks.add(task);
    }

    public void delete(int taskNum){
        tasks.remove(taskNum);
    }

    public Task getTask(int taskNum){
        return tasks.get(taskNum);
    }

}
