import java.util.*;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskCounter = 0;
    protected static ArrayList<Task> taskArrList = new ArrayList<>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if(isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public String getDescription() {
        return this.description;
    }

    public static void addTask(Task t) {
        taskArrList.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        taskCounter++;
        if (taskCounter == 1) {
            System.out.println("Now you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
        }
    }

    public static void showTasks(){
        if (taskCounter == 1) {
            System.out.println("Here is the task in your list: ");
        } else if (taskCounter == 0) {
            System.out.println("You have no tasks in your list :(");
        } else {
            System.out.println("Here are the tasks in your list: ");
        }
        int i = 1;
        for (Task t : taskArrList) {
            System.out.println((i) + "." + taskArrList.get(i-1).toString());
            i += 1;
        }
    }

    public static void taskDone(String input) {
        char charArr[] = input.toCharArray();
        String taskNum = "";
        for (int i = 5; i < charArr.length; i++) {
            taskNum += charArr[i];
        }
        int taskInt = Integer.parseInt(taskNum);
        taskInt -= 1;
        taskArrList.get(taskInt).isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskArrList.get(taskInt).getStatusIcon() + "] " + taskArrList.get(taskInt).getDescription());
    }

    public static void deleteTask(String input) {
        int taskInt = Integer.parseInt(input);
        taskInt -= 1;
        Task task = taskArrList.get(taskInt);
        taskCounter--;
        System.out.println("Noted. I've removed this task:");
        System.out.println("  [" + taskArrList.get(taskInt).getStatusIcon() + "] " + taskArrList.get(taskInt).getDescription());
        if (taskCounter == 1) {
            System.out.println("Now you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
        }
        taskArrList.remove(taskInt);
    }
}