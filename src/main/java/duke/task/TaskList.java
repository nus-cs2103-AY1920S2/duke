package duke.task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private final static String TASK_DONE_MESSAGE = "UwU! Kirby has marked this task as done:";
    private final static String TASK_ADD_MESSAGE = "Poyo! Kirby added this task:";
    private final static String DELETE_MESSAGE = "OwO. Kirby has removed this task(s):";
    private final static String CLEAR_ALL_MESSAGE = "Kirby has cleared all the tasks in the list UwU";
    private final static Comparator<Task> TASK_COMPARATOR = new Comparator<Task>() {
        @Override
        public int compare(Task o1, Task o2) {
            int task1Priority = o1.priority;
            int task2Priority = o2.priority;

            return task1Priority - task2Priority;
        }
    };

    /**
     * TaskList holds the list of tasks that are in the Duke program.
     * TaskList is also the class that executes the given commands.
     *
     * @param storageData Data of the current Duke program.
     * @throws DukeException Storage data is invalid.
     */
    public TaskList(ArrayList<Task> storageData) throws DukeException{
        this.storageData = storageData;
        this.storageData.sort(TASK_COMPARATOR);
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
                output += "     " + counter + "." + task + "\n";
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

            int currentTaskSize = this.storageData.size();
            
            if (type.equals("todo")) {
                Task new_Task = new Todo(type, inputCommand);
                this.storageData.add(new_Task);
                assert(currentTaskSize + 1 == this.storageData.size());
            } else if (type.equals("deadline")) {
                Task new_Task = new Deadline(type, inputCommand);
                this.storageData.add(new_Task);
                assert(currentTaskSize + 1 == this.storageData.size());
            } else {
                assert(type.equals("event"));
                Task new_Task = new Event(type, inputCommand);
                this.storageData.add(new_Task);
                assert(currentTaskSize + 1 == this.storageData.size());
            }
        } catch (DukeException dukeException) {
            String output = dukeException.toString();
            return output;
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
        int currentTaskSize = this.storageData.size();

        try {
            output += DELETE_MESSAGE + "\n" + "       " + this.storageData.get(indexPosition - 1) + "\n";
            output += "Now you have " + (this.storageData.size() - 1) + " task(s) in the list.";
            this.storageData.remove(indexPosition - 1);

            assert(currentTaskSize - 1 == this.storageData.size());
        } catch (IndexOutOfBoundsException e) {
            output = "OOPS! That number is not valid. You have " +
                    this.storageData.size() + " task(s) in your list.";
        }

        return output;
    }

/*    public String delete(String ...a) {

        String output = "";
        ArrayList<Integer> positions = new ArrayList<Integer>();
        ArrayList<Task> deletedTasks = new ArrayList<Task>();

        for (String i: a) {
            int position = Integer.parseInt(i);
            positions.add(position);
            deletedTasks.add(this.storageData.get(position));
        }

        Collections.sort(positions);
        for (int i = positions.size(); i > 0; i--) {
            this.storageData.remove(i - 1);
        }

        output += DELETE_MESSAGE + "\n";
        for (int i = 0; i < deletedTasks.size(); i++) {
            output += "       " + deletedTasks.get(i) + "\n";
        }

        output += "Now you have " + (this.storageData.size() - 1) + " task(s) in the list.";

        return output;
    }*/

    /**
     * Marks a task as done.
     *
     * @param position Position of the task in the list.
     */
    public String done(String position) {

        int taskDone = Integer.parseInt(position);

        this.storageData.get(taskDone - 1).markTaskAsDone();
        assert(this.storageData.get(taskDone - 1).isDone == true);

        String output = TASK_DONE_MESSAGE;
        output += "\n" + this.storageData.get(taskDone - 1);

        return output;
    }

    /**
     * Assign priority to a specific task in the list.
     *
     * @param position Position of the task in the list.
     * @param priority Priority that the user wants to set to.
     * @return Message indicating success.
     */
    public String priority(String position, String priority) {

        int task = Integer.parseInt(position);

        String output = this.storageData.get(task - 1).assignPriority(priority);
        this.storageData.sort(TASK_COMPARATOR);

        return output;
    }

    /**
     * Clears the list.
     *
     * @return Message indicating list cleared.
     */
    public String clearAll() {

        this.storageData.clear();
        String output = CLEAR_ALL_MESSAGE;

        return output;
    }
}
