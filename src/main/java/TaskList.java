import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> arrList;

    /**
     * Constructor. To create and bind an arrayList
     */
    public TaskList() {
        arrList = new ArrayList<Task>();
    }

    /**
     * Delete task at index taskNum
     * @param taskNum index at which to delete task
     */
    public void deleteTask(int taskNum) {
        Task removedTask = arrList.get(taskNum - 1);
        arrList.remove(taskNum - 1);
        Ui.printDelete(removedTask, arrList);
    }

    /**
     * Marks task at index taskNum as done
     * @param taskNum index at which to mark the task
     */
    public void taskDone(int taskNum) {
        Task doneTask = arrList.get(taskNum - 1);
        doneTask.toggleDone();
        Ui.printDone(doneTask, arrList);
    }

    /**
     * Adds a Task to TaskList
     * @param task Task to be added
     */
    public void addList(Task task) {
        arrList.add(task);
        Ui.printAdd(task, arrList);
    }
}
