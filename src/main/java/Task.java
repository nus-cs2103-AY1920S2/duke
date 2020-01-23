import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;

    public static ArrayList<Task> tasks = new ArrayList<>();

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

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public static void addTask(Task task) {
        tasks.add(task);

        System.out.println("\tAlright! The following task has been added:");
        System.out.format("\t\t%s%n", task);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
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
    
    public static Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }
    
    public static int getTotalNumOfTasks() {
        return tasks.size();
    }
    
    public static void removeTask(int taskNumber) {
        Task temp = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        System.out.println("\tNoted. I have removed this task:");
        System.out.format("\t\t%s%n", temp);
        System.out.format("\tYou now have %d %s in the list.%n%n",
                tasks.size(), tasks.size() == 1 ? "task" : "tasks");
    }
    
    public static void setAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }
}
