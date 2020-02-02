import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<>();

    Ui ui = new Ui();

    public TaskList(ArrayList tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getTaskListSize() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public void findTask(String description) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(description)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() != 0) {
            ui.showLine();
            System.out.println("Found your matching tasks in your list:");
            for (int i = 0; i < foundTasks.size(); i++) {
                System.out.println(i + 1 + ". " + foundTasks.get(i).toString());
            }
            ui.showLine();
        } else {
            ui.showLine();
            System.out.println("There are no matching tasks in your list");
            ui.showLine();
        }

    }


}
