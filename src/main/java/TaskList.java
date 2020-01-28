import java.util.List;

public class TaskList {

    private List<Task> listOfTask;
    private Ui ui;

    public TaskList() {};

    public TaskList(List<Task> listOfTask, Ui ui) {
        this.listOfTask = listOfTask;
        this.ui = ui;
    }

    public void add(Task mytask) {
        listOfTask.add(mytask);
        ui.addMessage((this.listOfTask).size(), mytask);
    }

    public void delete(int index) {
        // Split the string to get the
        // index of the task to be deleted
        ui.deletedTaskMessage(getsize() - 1, listOfTask.get(index));
        listOfTask.remove(index); // Deletes from task list

    }

    public List<Task> getListOfTask() {
        return listOfTask;
    }

    public int getsize() {
        return listOfTask.size();
    }
}
