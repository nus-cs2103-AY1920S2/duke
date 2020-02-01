package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import duke.exceptions.IncorrectArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Class which contains the list of Task entities, and contains methods supporting the addition and removal of tasks. */
class TaskList {
    /** List of tasks; assume no more than 100 tasks. */
    private ArrayList<Task> lTasks = new ArrayList<>();

    /**
     * Loads tasks from the list returned by Storage if there are no tasks already loaded.
     * @param tasks the list of tasks returned by storage
     * @return true if the tasks were successfully loaded and false otherwise.
     */
    public boolean load(ArrayList<Task> tasks) {
        if (lTasks.isEmpty()) {
            lTasks.addAll(tasks);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Passes a copy of the list of Tasks to Storage for saving.
     * @return a list of Tasks.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> save() {
        return ((ArrayList<Task>) lTasks.clone());
    }

    /**
     * Adds a Todo task to the Task List.
     * @param desc description of the Todo Task.
     * @return A string describing the Task that was added.
     * @throws IncorrectArgumentException
     */
    public String[] addTodo(String desc) {
        return add(new Todo(desc));
    }
    
    /**
     * Adds a Task of type Deadline to the list.
     * @param desc the description of the Task.
     * @param date the date the task is due by.
     * @param time the time the task is due by.
     * @return a String describing the Log that is displayed by the Ui after the task is executed.
     */
    public String[] addDeadline (String desc, LocalDate date, LocalTime time) {
        return add(new Deadline(desc, date, time));
    }
    
    /**
     * Adds a Task of type Event to the list.
     * @param desc the description of the Task.
     * @param date the date the task occurs at.
     * @param time the time the task occurs at.
     * @return a String describing the Log that is displayed by the Ui after the task is executed.
     */
    public String[] addEvent (String desc, LocalDate date, LocalTime time) {
        return add(new Event(desc, date, time));
    }

    /**
     * Adds a Task to the list
     * @param s the task to be added
     * @return a log describing the Task added after execution.
     */
    private String[] add(Task s) {
        lTasks.add(s);
        return new String[]{
                "Got it. I've added this task:", 
                "  " + s.toString(),
                String.format("Now you have %d tasks in the list.", lTasks.size())
            };
    }

    /**
     * Lists out the tasks currently in the TaskList
     * @return a string describing the list of tasks to be displayed by the Ui
     */
    public String[] list() {
        String[] tasks = new String[lTasks.size() + 1];
        tasks[0] = lTasks.size() == 0 
            ? "You have no tasks in your list." 
            : "Here are the tasks in your list:";
        for (int i = 1; i <= lTasks.size(); i++) {
            tasks[i] = String.format("%d.%s", i, lTasks.get(i - 1).toString());
        }
        return tasks;
    }

    /**
     * Sets the status of a Task in the list to 'Done'
     * @param i the index of the task in the list to be marked as 'Done'
     * @return a String logging the change being executed.
     */
    public String[] done(int i) {
        return new String[]{ "Nice! I've marked this task as done:", lTasks.get(i - 1).done() };
    }

    /**
     * Deletes a Task from the list of Tasks
     * @param i the index of the task being deleted
     * @return a String logging the change being executed.
     */
    public String[] delete(int i) {
        Task rem = lTasks.remove(i - 1);
        return new String[]{
                "Noted. I've removed this task:", 
                rem.toString(),
                String.format("Now you have %d tasks in the list.", lTasks.size())
            };
    }
}