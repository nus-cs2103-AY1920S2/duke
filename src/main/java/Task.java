import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected static List<Task> taskList = new ArrayList<>(100);

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void initTasks(List<Task> tasks) {
        taskList = tasks;
    }

    public static List<Task> getTaskList() {
        return taskList;
    }

    private static int getTaskCount() {
        return taskList.size();
    }

    public static void setDone(String num) {
        try {
            Task completedTask = taskList.get(Integer.parseInt(num) - 1);
            completedTask.isDone = true;
            System.out.print("Nice, I've marked this as done:");
            System.out.println(completedTask);
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"done number\", make sure number exists in list!");
        }
    }

    public static void deleteTask(String num) {
        try {
            Task deletedTask = taskList.remove(Integer.parseInt(num) - 1);
            System.out.print("Deleted: ");
            System.out.println(deletedTask);
            System.out.format("You now have %d tasks in the list\n", getTaskCount());
        } catch (Exception e) {
            throw new InvalidFormatException("Enter \"delete number\", make sure number exists in list!");
        }
    }

    public static void printTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("Nothing at the moment, you're all good.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.format("%s." + taskList.get(i) + '\n', String.valueOf(i + 1));
            }
        }
    }

    public static void addNewTask(String[] instArr) {
        List instList = Arrays.asList(instArr);
        if (instArr[0].equals("todo")) {
            String description = String.join(" ", Arrays.copyOfRange(instArr, 1, instArr.length));
            Task newTask = new ToDo(description);
            AddTaskHelper(newTask);
        } else if (instArr[0].equals("deadline")) {
            // exception to handle non existence of /by and correspondingly /at
            int seperator = instList.indexOf("/by");
            if (seperator == -1) {
                throw new InvalidFormatException("correct format: deadline task /by date");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, seperator));
            String dateTime = String.join(" ", Arrays.copyOfRange(instArr, seperator + 1, instArr.length));
            try {
                LocalDateTime by = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                Task newTask = new Deadline(description, by);
                AddTaskHelper(newTask);
            } catch (DateTimeParseException e) {
                throw new InvalidFormatException("Make sure you entered valid date: yyyy-MM-dd HH:mm");
            }
        } else if (instArr[0].equals("event")) {
            int seperator = instList.indexOf("/at");
            if (seperator == -1) {
                throw new InvalidFormatException("correct format: event task /at place");
            }
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, seperator));
            String at = String.join(" ", Arrays.copyOfRange(instArr, seperator + 1, instArr.length));
            Task newTask = new Event(description, at);
            AddTaskHelper(newTask);
        }
    }

    private static void AddTaskHelper(Task addedTask) {
        taskList.add(addedTask);
        System.out.print("Added: ");
        System.out.println(addedTask);
        System.out.format("You now have %d tasks in the list\n", getTaskCount());
    }

    public String getStatusIcon() {
        // return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or X symbols
        return (isDone ? "[✓]" : "[✗]");    // the above did not work.
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
