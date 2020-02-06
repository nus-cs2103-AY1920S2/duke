package duke.commands;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 *  Contains the task list.
 */
public class TaskList {

    /**
     * The list containing all the tasks.
     */
    private ArrayList<Task> newList;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        this.newList = new ArrayList<>();
    }

    /**
     * Lists out all the tasks currently available in the Tasklist.
     *
     * @return response to "list" command
     */
    public String list() {
        String output = "";
        if (newList.size() == 0) {
            output += ("You currently have no tasks in your list\n");
        } else {
            output += "Here are the tasks in your list:\n";
            for (int i = 0; i < newList.size(); i += 1) {
                output += ((i + 1) + ". " + newList.get(i).toString() + "\n");
                assert newList.get(i) != null : "No task to list out";
                assert newList.get(i).getDescription() != null : "No description for this task";
            }
        }
        return output;
    }

    /**
     * Marks the specified task as done.
     *
     * @param index the index of the task
     * @return response to "done" command
     */
    public String done(int index) {
        assert newList.size() > 0 : "No tasks in list";
        newList.get(index).markAsDone();
        assert newList.get(index) != null : "No task to mark as done";
        assert newList.get(index).getDescription() != null : "No description for this task";
        return ("Nice! I've marked this task as done: \n"
                + newList.get(index).toString() + "\n");
    }

    /**
     * Deletes the specified task.
     *
     * @param index the index of the task
     * @return response to "delete" command
     */
    public String delete(int index) {
        assert newList.size() > 0 : "No tasks in list";
        Task task = newList.get(index);
        assert newList.get(index) != null : "No task to delete";
        assert newList.get(index).getDescription() != null : "No description for this task";
        newList.remove(index);
        return ("Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + newList.size() + " tasks in the list.\n");
    }

    /**
     * Add a new task to the Tasklist.
     *
     * @param newTask the new task to be added
     * @param print whether to print out the notification that the task has been
     *              added or not
     * @return response to "add" command
     */
    public String add(Task newTask, boolean print) {
        newList.add(newTask);
        assert newTask != null : "No task to add";
        assert newTask.getDescription() != null : "No description for this task";
        if (print) {
            return ("Got it. I've added this task:\n"
                    + newTask.toString() + "\n"
                    + "Now you have " + newList.size() + " tasks in the list."
                    + "\n");
        } else {
            return null;
        }
    }

    /**
     * Returns the total number of tasks in Tasklist.
     *
     * @return the current number of tasks in the Tasklist
     */
    public int size() {
        return newList.size();
    }

    /**
     * Retrieve a specific Task from the Tasklist.
     *
     * @param index the index of the Task
     * @return the Task that was specified
     */
    public Task get(int index) {
        assert newList.get(index) != null : "No task to get";
        assert newList.get(index).getDescription() != null : "No description for this task";
        return newList.get(index);
    }

    /**
     * Returns a list of tasks that contain the patterns string in their
     * description.
     *
     * @param pattern the pattern to find
     * @return response to "find" command
     */
    public String find(String pattern) {
        if (newList.size() == 0) {
            return ("You currently have no tasks in your list\n");
        } else {
            String output = "";
            int count = 0;
            for (int i = 0; i < newList.size(); i += 1) {
                if (newList.get(i).toString().contains(pattern)
                        || newList.get(i).fileString().contains(pattern)) {
                    output += ((i + 1) + ". " + newList.get(i).toString()
                            + "\n");
                    count += 1;
                }
            }
            if (count == 0) {
                return ("There are no matching tasks in your list\n");
            } else {
               return "Here are the matching tasks in your list:\n" + output;
            }
        }
    }
}
