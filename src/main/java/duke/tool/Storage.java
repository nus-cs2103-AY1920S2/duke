package duke.tool;

import duke.command.Deadlines;
import duke.command.Events;
import duke.command.Task;
import duke.command.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private TaskList taskList;

    /**
     * Construct a Storage object.
     *
     * @param filePath Where the file tasks.txt is located
     * @param taskList ArrayList of String containing the tasks of the user
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        try {
            File taskFile = new File("data/dukeStorage.txt");
            if (!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.taskList = taskList;
    }

    /**
     * Reads from tasks.txt and stores the tasks into taskList.
     */
    public void readFromFile() {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            taskList.clear();
            while (s.hasNextLine()) {
                String taskString = s.nextLine();
                String[] split = taskString.split("\\|");
                String taskType = split[0];
                boolean isDone = split[1].equals("V");
                String taskDescription = split[2];

                switch (taskType) {
                    case "T":
                        Task toDos = new ToDos(isDone, taskDescription);
                        taskList.add(toDos);
                        break;
                    case "D":
                        String deadlineDateTime = split[3];
                        Task deadlines = new Deadlines(isDone, taskDescription, deadlineDateTime);
                        taskList.add(deadlines);
                        break;
                    case "E":
                        String eventDateTime = split[3];
                        Task events = new Events(isDone, taskDescription, eventDateTime);
                        taskList.add(events);
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            // System.out.println("File not found");
        }
    }

    /**
     * Saves the current tasks in taskList to tasks.txt.
     */
    public void saveToFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : taskList.getToDo()) {
                fw.write(task.fileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            // System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Clears all tasks in the file
     */
    public void clearFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            // System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public String readAllBytes(String filePath)
    {
        String content = "";

        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return content;
    }

}
