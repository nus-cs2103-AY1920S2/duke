import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task removeTask(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("Sorry! Task does not exist!");
        }
        return taskList.remove(index);
    }

    public Task markAsDone(int index) throws DukeException {
        if (index >= taskList.size()) {
            throw new DukeException("Sorry! Task does not exist!");
        }
        Task task = taskList.get(index);
        task.markAsDone();
        return task;
    }

    public String printNumTasks() {
        return "Number of tasks: " + taskList.size();
    }

    public String listTasks() {
        if (taskList.isEmpty()) {
            return "Nothing in the list, good job! " + new String(Character.toChars(0x1F60A));
        }
        String formattedString = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            formattedString += (i + 1) + ". " + this.taskList.get(i).getFullDescription() + "\n    ";
        }
        return formattedString.trim();
    }
}