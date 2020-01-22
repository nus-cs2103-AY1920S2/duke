public class Task {
    protected String description;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task(String description) {
        System.out.println("\tGot it. I've added this task: ");
        this.description = description;
        this.isDone = false;
        taskAdded();
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public static void taskAdded() {
        totalTasks++;
    }

    public static void taskDeleted() {
        totalTasks--;
    }

    public static void getTotalTasks() {
        if (totalTasks == 0) {
            System.out.println("\tNow you have no tasks in the list.");
        } else if (totalTasks == 1) {
            System.out.println(String.format("\tNow you have %d task in the list.", totalTasks));
        } else {
            System.out.println(String.format("\tNow you have %d tasks in the list.", totalTasks));
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
