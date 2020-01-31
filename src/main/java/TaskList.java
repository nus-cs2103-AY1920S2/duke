import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> arrList;

    public TaskList() {
        arrList = new ArrayList<Task>();
    }

    public void deleteTask(int taskNum) {
        Task removedTask = arrList.get(taskNum - 1);
        arrList.remove(taskNum - 1);
        Ui.printDelete(removedTask, arrList);
    }

    public void taskDone(int taskNum) {
        Task doneTask = arrList.get(taskNum - 1);
        doneTask.toggleDone();
        Ui.printDone(doneTask, arrList);
    }

    public void addList(Task task) {
        arrList.add(task);
        Ui.printAdd(task, arrList);
    }
}
