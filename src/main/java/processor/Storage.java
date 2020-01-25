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

public class Storage {

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
