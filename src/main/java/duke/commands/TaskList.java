package duke.commands;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 *  contains the task list.
 */
public class TaskList {

    /**
     * the list containing all the tasks.
     */
    private ArrayList<Task> newList;
    /**
     * creates a new Ui object to access functions.
     */
    private Ui ui = new Ui();

    /**
     * creates a new TaskList object.
     */
    public TaskList() {
        this.newList = new ArrayList<>();
    }

    /**
     * lists out all the tasks currently available in the Tasklist.
     */
    public void list() {
        if (newList.size() == 0) {
            ui.dukePrint("You currently have no tasks in your list\n");
        } else {
            System.out.print(ui.horizontalLines() + "Here are the tasks in your"
                    + " list:\n");
            for (int i = 0; i < newList.size(); i += 1) {
                System.out.print((i + 1) + ". " + newList.get(i).toString()
                        + "\n");
            }
            System.out.print(ui.horizontalLines());
        }
    }

    /**
     * marks the specified task as done.
     * @param i the index of the task
     */
    public void done(int i) {
        newList.get(i).markAsDone();
        ui.dukePrint("Nice! I've marked this task as done: \n"
                + newList.get(i).toString() + "\n");
    }

    /**
     * deletes the specified task.
     * @param i the index of the task
     */
    public void delete(int i) {
        Task task = newList.get(i);
        newList.remove(i);
        ui.dukePrint("Noted. I've removed this task:\n" + task.toString() + "\n"
                + "Now you have " + newList.size() + " tasks in the list.\n");
    }

    /**
     * add a new task to the Tasklist.
     * @param newTask the new task to be added
     * @param command whether to notify the user that the task has been added
     *                or not
     */
    public void add(Task newTask, String command) {
        newList.add(newTask);
        if (command.equals("print")) {
            ui.dukePrint("Got it. I've added this task:\n"
                    + newList.get(newList.size() - 1).toString() + "\n"
                    + "Now you have " + newList.size() + " tasks in the list."
                    + "\n");
        }
    }

    /**
     * returns the total number of tasks in Tasklist.
     * @return the current number of tasks in the Tasklist
     */
    public int size() {
        return newList.size();
    }

    /**
     * retrieve a specific Task from the Tasklist.
     * @param index the index of the Task
     * @return the Task that was specified
     */
    public Task get(int index) {
        return newList.get(index);
    }

    /**
     * returns a list of tasks that contain the patterns string in their
     * description.
     * @param pattern the pattern to find
     */
    public void find(String pattern) {
        if (newList.size() == 0) {
            ui.dukePrint("You currently have no tasks in your list\n");
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
                ui.dukePrint("There are no matching tasks in your list\n");
            } else {
                System.out.print(ui.horizontalLines() + "Here are the matching "
                        + "tasks in your list:\n");
                System.out.println(output);
                System.out.print(ui.horizontalLines());
            }
        }
    }
}
