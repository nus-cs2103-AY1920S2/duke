import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        tasks = new ArrayList<Task>();
        size = 0;
    }

    public void printTasksContaining(String sequence) {
        for (int i = 1; i <= size; i++) {
            Task task = tasks.get(i - 1);
            if (task.getDetails().contains(sequence)) {
                System.out.println("     " + i + "." + task);
            }
        }
        //tasks.forEach(task -> task.getDetails().contains(sequence) ? );
    }

    public void addTask(Task t) {
        tasks.add(t);
        size++;
    }

    public void remTask(int index) {
        tasks.remove(index);
        size--;
    }

    public void markDone(int index) {
        tasks.get(index).markDone();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public String tasksToString() {
        String output = "";
        for (int i = 0; i < size; i++) {
            Task t = tasks.get(i);
            output += t.writeToFile() + "\n";
        }
        return output;
    }

    public void printTaskList() {
        for (int i = 1; i <= size; i++) {
            String item = "     " + i + "." + tasks.get(i - 1);
            System.out.println(item);
        }
    }

    public int getSize() {
        return size;
    }
}
