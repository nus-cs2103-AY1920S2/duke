package task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public int getSize() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int id) {
        return tasks.get(id);
    }

    public void delete(int id) {
        tasks.remove(id);
    }

    public void deleteAll() {
        tasks.clear();
    }

    /**
     * Represents a method to delete some tasks.
     * @param idOfTaskListToBeDeleted tasks to be deleted.
     */
    public void deleteSome(ArrayList<Integer> idOfTaskListToBeDeleted) {
        for (int i : idOfTaskListToBeDeleted) {
            tasks.remove(i - 1);
        }
    }

    /**
     * Represents a method to find tasks with keywords.
     * @param keyword keyword to find tasks.
     * @return return the list of tasks according to keyword.
     */
    public TaskList find(String keyword) {
        ArrayList<Task> newList = new ArrayList<>();
        for (Task t : tasks) {
            if (t.command.contains(keyword)) {
                newList.add(t);
            }
        }
        return new TaskList(newList);
    }

}