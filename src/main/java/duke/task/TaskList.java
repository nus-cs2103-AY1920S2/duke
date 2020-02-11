package duke.task;

import java.util.ArrayList;

import duke.DukeException;

/**
 * Holds all the tasks that have been input into the Duke program.
 * Execution of commands happen here.
 * Commands include: list, find, add, delete and done.
 *
 * @author Dargo
 */
public class TaskList {

    private ArrayList<Task> storageData;
    private final static String LINE = "    ____________________________________________________________";

    private final static String LIST_HEADER = "Here are the tasks in your list:";
    private final static String FIND_HEADER = "Here are the matching tasks in your list:";
    private final static String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private final static String TASK_ADD_MESSAGE = "Got it. I've added this task:";
    private final static String DELETE_MESSAGE = "Noted. I've removed this task:";

    /**
     * TaskList holds the list of tasks that are in the Duke program.
     * TaskList is also the class that executes the given commands.
     *
     * @param storageData Data of the current Duke program.
     * @throws DukeException Storage data is invalid.
     */
    public TaskList(ArrayList<Task> storageData) throws DukeException{
        this.storageData = storageData;
    }

    /**
     * Get the ArrayList of tasks of the current Duke program.
     *
     * @return Current ArrayList of tasks of Duke.
     */
    public ArrayList<Task> getData() {
        return this.storageData;
    }

    /**
     * List out the tasks in Duke at the moment.
     */
    public String list() {
        String output = LIST_HEADER + "\n";
        for (int i = 0; i < storageData.size(); i++) {
            output += "     " + (i + 1) + "." + storageData.get(i) + "\n";
        }

        return output;
    }

    /**
     * Find the tasks with the given keyword and list them out.
     *
     * @param keyword
     */
    public String find(String keyword) {
        String output = FIND_HEADER;

        int counter = 1;
        for (int i = 0; i < storageData.size(); i++) {
            Task task = storageData.get(i);
            String taskFullInput = task.task;
            if(taskFullInput.contains(keyword)) {
                output += "     " + counter + "." + task;
                counter++;
            }
        }

        return output;
    }

    /**
     * Add tasks into the Duke program.
     *
     * @param type Type of task.
     * @param inputCommand Full input command.
     */
    public String add(String type, String inputCommand) {

        try {
            
            if (type.equals("todo")) {
                Task new_Task = new Todo(type, inputCommand);
                this.storageData.add(new_Task);
            } else if (type.equals("deadline")) {
                Task new_Task = new Deadline(type, inputCommand);
                this.storageData.add(new_Task);
            } else {
                Task new_Task = new Event(type, inputCommand);
                this.storageData.add(new_Task);
            }
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        } catch (Exception e) {
            System.out.println(e);
        }

        String output = TASK_ADD_MESSAGE + "\n" +
                "       " + this.storageData.get(this.storageData.size() - 1) + "\n";
        output += "Now you have " + this.storageData.size() + " task(s) in the list.";

        return output;
    }

    /**
     * Deletes a task from Duke.
     *
     * @param position Position of the task in the list.
     */
    public String delete(String position) {

        String output = "";

        int indexPosition = Integer.parseInt(position);

        try {
            output += DELETE_MESSAGE + "\n" + "       " + this.storageData.get(indexPosition - 1) + "\n";
            output += "Now you have " + (this.storageData.size() - 1) + " task(s) in the list.";
            this.storageData.remove(indexPosition - 1);
        } catch (IndexOutOfBoundsException e) {
            output = "OOPS! That number is not valid. You have " +
                    this.storageData.size() + " task(s) in your list.";
        }

        return output;
    }

    /**
     * Marks a task as done.
     *
     * @param position Position of the task in the list.
     */
    public String done(String position) {

        int task_Done = Integer.parseInt(position);

        this.storageData.get(task_Done - 1).taskDone();

        String output = TASK_DONE_MESSAGE;
        output += "\n" + this.storageData.get(task_Done - 1);

        return output;
    }
}
