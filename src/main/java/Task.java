public class Task {
    protected String description;
    protected boolean isDone;

    public static Task[] tasks = new Task[100];
    public static int taskIdx = 0;

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
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static void addTask(Task task) {
        tasks[taskIdx] = task;
        taskIdx++;
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks:");

        if (taskIdx == 0) {
            System.out.println("\t<No tasks have been added>");
        }

        for (int i = 0; i < taskIdx; i++) {
            Task currTask = tasks[i];
            System.out.format("%s%d.%s%n", "\t", i + 1, currTask);
        }

        System.out.println();
    }

    public static void printAddedTask(Task task) {
        System.out.println("\tAlright! The following task has been added:");
        System.out.format("%s%s%n", "\t\t", task);
        System.out.format("\tYou now have %d %s in the list.%n%n", taskIdx, taskIdx == 1 ? "task" : "tasks");
    }

    public static void printMarkedAsDone(Task task) {
        System.out.println("\tNoted. I have marked this task as done:");
        System.out.format("%s%s%n%n", "\t\t", task);
    }
}
