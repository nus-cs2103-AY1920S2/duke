import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;

    public static ArrayList<Task> tasks = new ArrayList<>();
    private static final String DATAFILE = "data/file.txt";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");  // return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        
        System.out.println("\tNoted. I have marked this task as done:");
        System.out.format("\t\t%s%n%n", this);
    }

    protected String getFileFormattedLine() {
        return String.format("G|%s|%s", this.isDone ? "1" : "0", this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static int getTotalNumOfTasks() {
        return tasks.size();
    }

    public static void loadInTaskFromFileLine(String line) {
        String[] lineArr = line.split("\\|");  // escape special char |
        String type = lineArr[0];
        String doneStatus = lineArr[1];
        String description = lineArr[2];
        Task task;

        if (type.equals("T")) {
            task = new Todo(description);
        } else if (type.equals("D")) {
            task = new Deadline(description, lineArr[3]);
        } else {
            task = new Event(description, lineArr[3]);
        }

        if (doneStatus.equals("1")) {
            task.isDone = true;
        }

        tasks.add(task);
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks:");

        if (tasks.isEmpty()) {
            System.out.println("\t<No tasks have been added>");
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.format("\t%d.%s%n", i + 1, tasks.get(i));
        }
        
        System.out.println();
    }

    public static void setAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
        
        try {
            updateTaskInFileToDone(taskNumber);
        } catch (IOException e) {
            System.out.println("\n\tError: Unable to update saved tasks file.\n");
        }
    }

    public static void updateTaskInFileToDone(int taskNumber) throws IOException {
        File file = new File(DATAFILE);
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        int lineCount = 1;

        while (sc.hasNextLine()) {
            if (lineCount == taskNumber) {
                String line = sc.nextLine();
                String[] lineArr = line.split("\\|");
                lineArr[1] = "1";
                String newLine = String.join("|", lineArr);
                sb.append(newLine).append(System.lineSeparator());
            } else {
                sb.append(sc.nextLine()).append(System.lineSeparator());
            }
            lineCount++;
        }

        FileWriter fw = new FileWriter(DATAFILE, false);  // overwrite entire file
        fw.write(sb.toString());
        fw.close();
    }
    
    public static void addTask(Task task) {
        tasks.add(task);

        System.out.println("\tAlright! The following task has been added:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");

        try {
            appendTaskToFile(task);
        } catch (IOException e) {
            System.out.println("\n\tError: Unable to write to saved tasks file.\n");
        }
    }
    
    public static void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(DATAFILE, true);
        fw.append(task.getFileFormattedLine()).append(System.lineSeparator());
        fw.close();
    }
    
    public static void removeTask(int taskNumber) {
        Task temp = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        System.out.println("\tNoted. I have removed this task:");
        System.out.format("\t\t%s%n", temp);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");

        try {
            removeTaskFromFile(taskNumber);
        } catch (IOException e) {
            System.out.println("\n\tError: Unable to edit saved tasks file.\n");
        }
    }
    
    private static void removeTaskFromFile(int taskNumber) throws IOException {
        File file = new File(DATAFILE);
        Scanner sc = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        int lineCount = 1;

        while (sc.hasNextLine()) {
            if (lineCount != taskNumber) {
                sb.append(sc.nextLine()).append(System.lineSeparator());
            } else {
                sc.nextLine();
            }
            lineCount++;
        }

        FileWriter fw = new FileWriter(DATAFILE, false);  // overwrite entire file
        fw.write(sb.toString());
        fw.close();
    }
}
