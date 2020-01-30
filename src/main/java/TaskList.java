import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int index) throws InvalidIndexException{
        try {
            return this.taskList.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry dude but this index is nowhere to be found.");
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public int getListSize() {
        return this.taskList.size();
    }

    public void printTaskList(Ui ui) {
        if (this.taskList.isEmpty()) {
            ui.printEmptyListMessage();
        } else {
            ui.printTasks(this.taskList);
        }
    }

    public Task addTask(String commandWord, String[] commands) {
        Task task = null;
        String description;
        String[] descriptions;
        switch (commandWord) {
            case "todo":
                description = commands[1];
                task = new Todo(false, description);
                break;
            case "event":
                descriptions = commands[1].split(" /at ");
                task = new Event(false, descriptions[0], descriptions[1]);
                break;
            case "deadline":
                descriptions = commands[1].split(" /by ");
                task = new Deadline(false, descriptions[0], descriptions[1]);
                break;
        }
        this.taskList.add(task);
        return task;
    }

    public void markTaskAsDone(int doneIndex) {
        Task task = this.taskList.get(doneIndex);
        task.markAsDone();
    }

    public void deleteTask(int deleteIndex) {
        this.taskList.remove(deleteIndex );
    }
}
