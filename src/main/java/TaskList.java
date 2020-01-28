import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public int size() {
        return taskList.size();
    }

    public void printTaskList(Ui ui) {
        ui.showLine();
        ui.print("This is your list of tasks:");
        for (Task t : taskList) {
            ui.print(t.toString());
        }
    }

    public void completeTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task t = taskList.get(taskNumber - 1);
        ui.print("This task is marked as completed:");
        ui.print(t.toString());
        t.markAsDone();
        storage.updateTasks(taskList);
    }

    public void deleteTask(Ui ui, Storage storage, int taskNumber) throws DukeException {
        if (taskNumber > taskList.size() || taskNumber < 1) {
            throw new DukeException("This task number does not exist.");
        }
        Task t = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        ui.print("This task has been deleted:");
        ui.print(t.toString());
        storage.updateTasks(taskList);
    }
}
