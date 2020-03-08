package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList contains the list of tasks and methods for the list.
 */
public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns specific task required.
     * @param index Index of task in the list.
     * @return Required task.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Returns number of tasks in list.
     * @return size of list.
     */
    public int getListSize() {
        return list.size();
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(Task task) {
        list.remove(task);
    }

    /**
     * Marks a few specified tasks as done.
     * @param indexList List of indices of the tasks specified.
     * @return TaskList consisting of the tasks marked done.
     */
    public TaskList doneSpecificTasks(ArrayList<Integer> indexList) {
        ArrayList<Task> doneList = new ArrayList<>();
        for (int i = 0; i < indexList.size(); i++) {
            getTask(indexList.get(i) - 1).markDone();
            doneList.add(getTask(indexList.get(i) - 1));
        }
        return new TaskList(doneList);
    }

    /**
     * Finds tasks containing the keyword.
     * @param key Keyword.
     * @return TaskList consisting of tasks that has the keyword.
     */
    public TaskList findMatching(String key) {
        ArrayList<Task> resultsList = new ArrayList<Task>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(key)) {
                resultsList.add(list.get(i));
            }
        }
        return new TaskList(resultsList);
    }
}