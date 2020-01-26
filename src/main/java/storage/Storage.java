package storage;

import task.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

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

    public void save(TaskList taskList) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.toStringDukeTasks());
        fw.close();
    }
}
