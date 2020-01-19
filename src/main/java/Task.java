import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;
    protected static List<Task> taskList = new ArrayList<>(100);

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
    }

    public static void setDone(int index) {
        Task completedTask = taskList.get(index);
        completedTask.isDone = true;
        System.out.print("Nice, I've marked this as done:");
        System.out.println(completedTask);
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
            printAddedTask(newTask);
        } else if (instArr[0].equals("deadline")) {
            // exception to handle non existence of /by and correspondingly /at
            int seperator = instList.indexOf("/by");
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, seperator));
            String by = String.join(" ", Arrays.copyOfRange(instArr, seperator + 1, instArr.length));
            Task newTask = new Deadline(description, by);
            printAddedTask(newTask);
        } else if (instArr[0].equals("event")) {
            int seperator = instList.indexOf("/at");
            String description =  String.join(" ", Arrays.copyOfRange(instArr, 1, seperator));
            String at = String.join(" ", Arrays.copyOfRange(instArr, seperator + 1, instArr.length));
            Task newTask = new Event(description, at);
            printAddedTask(newTask);
        }  /*
            else {
            // handle invalid task type entered as exception in level 5
            System.out.println("Invalid task type");
        }
        */
    }

    private static void printAddedTask(Task addedTask) {
        taskList.add(addedTask);
        System.out.print("Added: ");
        System.out.println(addedTask);
        System.out.format("You now have %d tasks in the list\n", taskCount);
    }

    public String getStatusIcon() {
        // return (isDone ? "[\u2713]" : "[\u2718]"); // return tick or X symbols
        return (isDone ? "[✓]" : "[✗]");    // the above did not work.
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
