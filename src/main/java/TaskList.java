import java.util.ArrayList;

import dukeexception.DukeUnknownInputException;
import javafx.util.Pair;

import dukeexception.DukeException;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

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
    public Task markDone(int taskNum) throws DukeException {
        if (taskNum > tasks.size() || taskNum < 0) {
            throw new DukeException("Task for this number doesn't exist.");
        }
        int index = taskNum - 1;
        assert index >= 0;
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
        assert index >= 0;
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Gets a list of tasks that contains the word user is finding.
     *
     * @param word The word user wants to find.
     * @return List of Pair of Integer and Task, where int is the task number.
     */
    public ArrayList<Pair<Integer, Task>> find(String word) {
        ArrayList<Pair<Integer, Task>> tasksFound = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(word)) {
                tasksFound.add(new Pair<>(i, currTask));
            }
        }
        return tasksFound;
    }

    /**
     * Updates a task in the tasks list.
     *
     * @param taskNum The task number to be updated.
     * @param code T for time, D for description to decide which to be updated.
     * @param change Information to be changed to.
     * @return The updated task.
     * @throws DukeUnknownInputException If user tries to change time of a Todo task.
     */
    public Task update(int taskNum, String code, String change) throws DukeUnknownInputException {
        int index = taskNum - 1;
        assert index >= 0;
        Task currTask = tasks.get(index);
        Task updatedTask = null;
        if (currTask instanceof Todo) {
            if (code.equals("T")) {
                throw new DukeUnknownInputException("Sorry but Todo events have no time.\n"
                        + "Did you mean to type:\n"
                        + "update " + taskNum + " D " + change);
            } else {
                updatedTask = new Todo(change, currTask.getisDone());
            }
        } else if (currTask instanceof Deadline) {
            if (code.equals("D")) {
                updatedTask = new Deadline(change, ((Deadline)currTask).getBy(), currTask.getisDone());
            } else {
                updatedTask = new Deadline(currTask.getDescription(), change, currTask.getisDone());
            }
        } else {
            if (code.equals("D")) {
                updatedTask = new Event(change, ((Event)currTask).getAt(), currTask.getisDone());
            } else {
                updatedTask = new Event(currTask.getDescription(), change, currTask.getisDone());
            }
        }
        tasks.set(index, updatedTask);
        return updatedTask;
    }
}
