package storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;
/**
 * Loads or save the storage file as requested. A <code>Storage</code> object corresponds to a file represented by the
 * file path e.g., <code>"duke/src/java"</code>
 */
public class Storage {
    private String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }
    /**
     * Loads the task list at the start of the program.
     * If the storage file cannot be found, a new one is created and loads that one instead, and tells the user that a
     * new storage file has been created.
     * @return The task list.
     * @throws IOException If the storage file cannot be found.
     */
    public ArrayList<Task> load() throws IOException{
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskString = nextLine.split("/");
            String taskTitle = taskString[0];
            switch (taskTitle){
            case "T":
                Task todoTask = new Todo(taskString[2]);
                if(taskString[1].equals("1")){
                    todoTask.markDone();
                }
                tasks.add(todoTask);
                break;
            case "D":
                Task deadlineTask = new Deadline(taskString[2], LocalDate.parse(taskString[3]));
                if(taskString[1].equals("1")){
                    deadlineTask.markDone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                Task eventTask = new Event(taskString[2], LocalDate.parse(taskString[3]));
                if(taskString[1].equals("1")){
                    eventTask.markDone();
                }
                tasks.add(eventTask);
                break;
            default:
                break;
            }
        }
        return tasks;
    }
    /**
     * Updates the task list.
     * If the storage file cannot be found, an error message is shown.
     * @param taskList The task list to be saved to storage file.
     * @throws IOException If storage file cannot be found.
     */
    public void save(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.toStringDukeTasks());
        fw.close();
    }
}
