import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public Task getTask(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        return tasks.get(taskNum - 1);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
    
    public void setAsDone(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        this.tasks.get(taskNum - 1).markAsDone();
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void showTasks() {
        System.out.println("\tHere are your tasks:");

        if (this.tasks.isEmpty()) {
            System.out.println("\t<No tasks have been added>");
        }

        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.format("\t%d.%s%n", i + 1, this.tasks.get(i));
        }

        System.out.println();
    }

    public void deleteTask(int taskNum) throws InvalidInstructionException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new InvalidInstructionException(String.format("Task #%d does not exist", taskNum));
        }

        this.tasks.remove(taskNum - 1);
    }
}
