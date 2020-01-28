public class Task {
    protected String description;
    protected boolean isDone;
    protected static Task[] taskList = new Task[100];
    protected static int taskCounter = 0;

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
        taskList[taskCounter] = t;
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        taskCounter++;
        if (taskCounter == 1) {
            System.out.println("Now you have " + taskCounter + " task in the list.");
        } else {
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
        }
    }

    public static String showTasks(){
        String output = "";
        if (taskCounter == 1) {
            output += "Here is the task in your list: \n";
        } else if (taskCounter == 0) {
            output += "You have no tasks in your list :(";
        } else {
            output += "Here are the tasks in your list: \n";
        }
        for (int i = 0; i < taskCounter; i++) {
            if (i < taskCounter - 1) {
                output += (i + 1) + "." + taskList[i].toString() + "\n";
            } else {
                output += (i + 1) + "." + taskList[i].toString();
            }
        }
        return output;
    }

    public static void taskDone(String input) {
        char charArr[] = input.toCharArray();
        String taskNum = "";
        for (int i = 5; i < charArr.length; i++) {
            taskNum += charArr[i];
        }
        int taskInt = Integer.parseInt(taskNum);
        taskInt -= 1;
        taskList[taskInt].isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  [" + taskList[taskInt].getStatusIcon() + "] " + taskList[taskInt].getDescription());
    }
}