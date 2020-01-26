package processor;

import exceptions.DukeException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.TodoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the storing of tasks on a local text file.
 */
public class Storage {

    /**
     * Initiates the storage process by checking if a .txt file exists on the local computer. If it does not, it
     * creates a new file for input.
     */
    public static void init() {
        try {
            File taskFile = new File("data/tasks.txt");
            if(!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
                taskFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to save tasks to the created .txt file on the local computer. Tasks are first packaged before storage.
     * @param processor Duke's processor.
     * @throws IOException
     */
    public static void saveTasks(DukeProcessor processor) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        fw.write("");
        for(int i = 0; i < processor.getTaskList().size(); i ++) {
            Task task = processor.getTaskList().get(i);
            String taskStorageString = packageTask(task);
            fw.append(taskStorageString);

            if(i != processor.getTaskList().size() - 1) {
                fw.append(System.getProperty("line.separator"));
            }
        }

        fw.close();
    }

    /**
     * Attempts to load tasks from the .txt file on the local computer, and processes packaged tasks from the file
     * before returning the list of saved tasks.
     * @param processor Duke's processor.
     * @return The list of tasks saved previously by Duke.
     * @throws IOException
     * @throws DukeException
     */
    public static List<Task> loadTasks(DukeProcessor processor) throws IOException, DukeException {
        File taskFile = new File("data/tasks.txt");

        if(!taskFile.exists()) {
            throw new IOException("File not found");
        }

        Scanner sc = new Scanner(taskFile);

        List<Task> outputTaskList = new ArrayList<Task>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            Task task = processPackagedTask(line);
            outputTaskList.add(task);
        }

        if(outputTaskList.size() != 0) {
            Ui.print(String.format("Looks like you've saved some tasks before! You have %d tasks in your list.",
                    outputTaskList.size()));
        }

        return outputTaskList;
    }

    /**
     * Packages the tasks into a format that can be stored and re-processed when loaded in the future.
     * @param task Task to be packaged.
     * @return Task object in the form of a packaged String, ready to be stored.
     */
    private static String packageTask(Task task) {
        String output = "";
        int doneIndicator = 0;

        if(task.isDone()) {
            doneIndicator = 1;
        }

        if(task instanceof TodoTask) {
            output = String.format("%s^_^%d^_^%s", "TODO", doneIndicator,  task.getDescription());
        } else if(task instanceof DeadlineTask) {
            output = String.format("%s^_^%d^_^%s^_^%s", "DEADLINE", doneIndicator, task.getDescription(),
                    ((DeadlineTask) task).getDeadline());
        } else if(task instanceof EventTask) {
            output = String.format("%s^_^%d^_^%s^_^%s", "EVENT", doneIndicator, task.getDescription(),
                    ((EventTask) task).getEventTime());
        }

        return output;
    }

    /**
     * Attempts to process and create a Task object from the String read from the .txt file.
     * @param taskString String from .txt file to be processed.
     * @return Task object that was defined from the taskString.
     * @throws DukeException
     */
    private static Task processPackagedTask(String taskString) throws DukeException {
        Task outputTask;
        String[] taskArray = taskString.split("\\^_\\^", 4);

        if(taskArray[0].equals("TODO")) {
            outputTask = new TodoTask(taskArray[2]);
        } else if(taskArray[0].equals("DEADLINE")) {
            outputTask = new DeadlineTask(taskArray[2], taskArray[3]);
        } else {
            outputTask = new EventTask(taskArray[2], taskArray[3]);
        }

        if(Integer.parseInt(taskArray[1]) == 1) {
            outputTask.complete();
        }

        return outputTask;
    }
}
