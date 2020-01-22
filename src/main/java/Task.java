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

    public static void addTask(String description) {
        Task task = new Task(description);
        tasks[taskIdx] = task;
        taskIdx++;
    }

    public static void printTasks() {
        for (int i = 0; i < taskIdx; i++) {
            Task currTask = tasks[i];
            System.out.format("%d.[%s] %s%n", i + 1, currTask.getStatusIcon(), currTask.getDescription());
        }
        System.out.println();
    }

    public static void printMarkedAsDone(Task task) {
        System.out.println("Noted. I have marked this task as done:");
        System.out.format("    %s%n%n", task);
    }
}
