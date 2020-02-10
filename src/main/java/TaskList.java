import java.util.ArrayList;
import java.util.List;

//Wrapper class for the list of tasks
public class TaskList {
    private List<Task> allTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public void addInput(Task s) {
        this.allTasks.add(s);
        DukeUI.showCurrentListSize(allTasks.size());
    }

    public String printTasks() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("HERE IS YOUR LIST\n");
        for (Task s : allTasks) {
            sb.append( count+ ". " + s + "\n");
            count++;
        }
        return sb.toString();
    }

    public Task getTask(int n) {
        return this.allTasks.get(n-1);
    }

    public List<Task> getAllTasks() {
        return this.allTasks;
    }

    public void add(Task task) {
        this.allTasks.add(task);
    }

    public void markDone(int taskNo) {
        allTasks.get(taskNo - 1).markAsDone();
    }

    public void removeTask(int taskNo) {
        Task tempTask = allTasks.remove(taskNo - 1);
        System.out.println(" " + tempTask);
        DukeUI.showCurrentListSize(allTasks.size());
    }
}
