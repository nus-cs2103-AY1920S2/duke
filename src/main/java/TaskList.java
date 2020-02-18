import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper class for the list of Tasks
 */
public class TaskList {
    private List<Task> allTasks;
    private List<Task> archivedTasks;

    public TaskList() {
        allTasks = new ArrayList<>();
        archivedTasks = new ArrayList<>();
    }

    public Task addTask(Task s) {
        this.allTasks.add(s);
        return s;
    }

    /**
     * Prints current Task
     * @return List of Current Task
     */
    public String printTasks() {
        return getString(allTasks);
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

    public Task markDone(int taskNo) {
        return allTasks.get(taskNo - 1).markAsDone();
    }

    public String removeTask(int taskNo) {
        Task tempTask = allTasks.remove(taskNo - 1);
        return DukeUI.showDeleteMsg() + "\n" +
                " " + tempTask + "\n" +
                DukeUI.showCurrentListSize(allTasks.size());
    }

    public String archiveTask(int taskNo) {
        Task tempTask = allTasks.remove(taskNo - 1);
        archivedTasks.add(tempTask);
        return DukeUI.showArchivedMsg(tempTask);

    }

    public String archiveAll() {
        String archiveMessage = "";
        for (int i = 1; i <= allTasks.size(); i++) {
            archiveMessage += this.archiveTask(i);
        }
        return archiveMessage;
    }

    /**
     * Prints archived task
     * @return List of archived task
     */
    public String showArchived() {
        return getString(archivedTasks);
    }

    private String getString(List<Task> tasks) {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("HERE IS YOUR LIST\n");
        for (Task s : tasks) {
            sb.append(count).append(". ").append(s).append("\n");
            count++;
        }
        return sb.toString();
    }
}
