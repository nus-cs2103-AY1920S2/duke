import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> listOfTasks;

    /**
     * Constructor for TaskList class used for starting from an empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList class used when loading from save.
     *
     * @param listOfTasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * returns TaskList size.
     *
     * @return size of TaskList.
     */
    public int getNumOfTasks() {
        return listOfTasks.size();
    }

    /**
     * Returns task in TaskList at index provided.
     *
     * @param index index number provided.
     * @return task at index.
     * @throws DukeException when index is out of bounds.
     */
    public Task getTask(int index) throws DukeException {
        if (index >= listOfTasks.size() || index < 0) {
            throw new DukeException("Index out of bounds!");
        }
        return listOfTasks.get(index);
    }

    /**
     * Removes task from TaskList at index number provided.
     *
     * @param index index number provided.
     */
    public void removeTask(int index) {
        listOfTasks.remove(index);
    }

    /**
     * Adds a todo task to the TaskList.
     *
     * @param desc description of todo provided.
     */
    public void newToDo(String desc) {
        listOfTasks.add(new ToDo(desc));
    }

    /**
     * Adds an event task to the TaskList.
     *
     * @param desc   description of event provided.
     * @param timing timing for the event provided.
     */
    public void newEvent(String desc, String timing) {
        listOfTasks.add(new Event(desc, timing));
    }

    /**
     * Adds a deadline task to the TaskList.
     *
     * @param desc description of deadline provided.
     * @param by   due date for the deadline.
     */
    public void newDeadline(String desc, String by) {
        listOfTasks.add(new Deadline(desc, by));
    }

    /**
     * returns the TaskList.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return listOfTasks;
    }


    /**
     * Returns an TaskList with all the tasks containing the keyword.
     *
     * @param keyword word specified by user to search all tasks for.
     * @return TaskList of all tasks containing the keyword.
     */
    public TaskList getMatches(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            String[] descSplit = listOfTasks.get(i).getDescription().split(" ");
            for (int j = 0; j < descSplit.length; j++) {
                if (descSplit[j].equals(keyword)) {
                    matchingTasks.add(listOfTasks.get(i));
                    break;
                }
            }
        }
        TaskList tasks = new TaskList(matchingTasks);
        return tasks;
    }
}
