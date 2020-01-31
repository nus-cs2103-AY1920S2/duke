package duke.tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTaskAt(int index) {
        return taskList.get(index);
    }

    public Task setDone(int index) {
        taskList.get(index).setTaskDone();
        return taskList.get(index);
    }

    public String printTasksTotal() {
        return "Now you have " + this.getSize() + " tasks in the list";
    }

    public Task deleteTask(int index) {
        return taskList.remove(index);
    }

    public boolean addTask(Task task) {
        // TODO: To handle this add() == false?
        return taskList.add(task);
    }

    /**
     * Filter the tasks that contain the keyword as a substring.
     *
     * @param keyword String to find in the task names.
     * @return An ArrayList containing tasks that contain the keyword as a substring.
     */
    public ArrayList<Task> filterTasks(String keyword) {
        ArrayList<Task> filteredTasks = this.taskList;
        filteredTasks.removeIf((task) -> !task.getTaskName().toLowerCase().contains(keyword.toLowerCase()));
        return filteredTasks;
    }
}