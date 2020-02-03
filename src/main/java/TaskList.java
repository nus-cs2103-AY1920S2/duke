import java.util.ArrayList;
import dukeexception.DukeException;

/**
 * Contains the task list, which is an ArrayList of tasks.
 *  It has operations to add/delete tasks in the list.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> listOfTasks) {
        tasks = listOfTasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getLength() {
        return tasks.size();
    }

    /**
     * Marks a task in the list as done.
     *
     * @param taskNum The task number on the list.
     * @return The task after being marks as done.
     * @throws DukeException If taskNum is bigger than size of list or < 0
     */
    public Task markDone(int taskNum) {
        if (taskNum > tasks.size() || taskNum < 0) {
            throw new DukeException("Task for this number doesn't exist.");
        }
        int index = taskNum - 1;
        Task currTask = tasks.get(index);
        currTask.setDone();
        tasks.set(index, currTask);
        return currTask;
    }

    /**
     * Adds a ToDo task into the list.
     *
     * @param todo The description of the ToDo task.
     * @return The To Do task constructed.
     */
    public Task addTodo(String todo) {
        Task todoTask = new Todo(todo);
        tasks.add(todoTask);
        return todoTask;
    }

    /**
     * Adds a Deadline task into the list.
     *
     * @param deadline The description of the deadline task.
     * @param byWhen When the task is due by.
     * @return The Deadline task constructed.
     */
    public Task addDeadline(String deadline, String byWhen) {
        Task deadlineTask = new Deadline(deadline, byWhen);
        tasks.add(deadlineTask);
        return deadlineTask;
    }

    /**
     * Adds an Event task into the list.
     *
     * @param event The description of the event task.
     * @param atWhen When the task is at.
     * @return The Event task constructed.
     */
    public Task addEvent(String event, String atWhen) {
        Task eventTask = new Event(event, atWhen);
        tasks.add(eventTask);
        return eventTask;
    }

    /**
     * Deletes a task in the list.
     *
     * @param taskNum The task number in the list to be deleted.
     * @return The task that was deleted from the list.
     */
    public Task delete(int taskNum) {
        int index = taskNum - 1;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Gets a list of tasks that contains the word user is finding.
     *
     * @param word The word user wants to find.
     * @return List of tasks that contains ward.
     */
    public ArrayList<Task> find(String word) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(word)) {
                tasksFound.add(currTask);
            }
        }
        return tasksFound;
    }
}
